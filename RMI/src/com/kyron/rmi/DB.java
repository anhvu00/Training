 package com.kyron.rmi;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB implements DBAccess {

    public static final long NO_LOCK = -999999;
    public static final long LOCK_WHOLE_TABLE = -1;
    //C:\Anh\Java\_SJCD_Cert\work\suncertify\src\db
//    private static final String DB_FILE = "C:/Anh/Java/_Certification/work/suncertify/src/db/Data.db";
    private static final String DB_FILE = "C:/Temp/RMI/Data.db";
    private static final String DELETE_FLAG = "1";
    private static Logger logger = Logger.getLogger("DB");
    /**
     * This hashmap holds the lock information for records.
     */
    private LockManager locker = new LockManager();
    private RandomAccessFile ras;
    private HashMap fieldsMap;
    private DataInputStream dis;
    private int offset;
    private int magiccode;
    private int recordLength;
    private int recordLengthWithFlag; // recordLength + 1, account for the delete flag
    private int fieldsPerRec;
    private String[] fieldNames;
    private String dbFile;

    public DB(String dbFileName, int magicCookie) throws IOException {
        try {
            fieldsMap = new HashMap();

            dbFile = dbFileName;
            magiccode = magicCookie;

            DataInputStream dis = new DataInputStream(new FileInputStream(dbFile)); // 12.21.08

            int newMagicCode = dis.readInt();
            boolean isValid = (magiccode == newMagicCode);
            logger.fine("Magiccode in file : " + newMagicCode + " Magiccode given : " + magiccode + (isValid ? "DB File is valid" : "Mismatch in magiccode"));
            if (!isValid) {
                throw new IOException("Invalid data file.  Code does not match.");
            }

            recordLength = dis.readInt();
            recordLengthWithFlag = recordLength + 1;
            fieldsPerRec = dis.readShort();
            fieldNames = new String[fieldsPerRec];  // create list of field names
            offset = 10; // header bytes

            //System.out.println("Header: MagicNum, RecLen, FieldsPerRec");
            //System.out.println("" + magiccode + "," + recordLength + "," + fieldsPerRec);

            byte[] ba;
            short fldNameLen;
            String fldName;
            short fldLen;

            // read field description
            for (int i = 0; i < fieldsPerRec; i++) {
                fldNameLen = dis.readShort();
                ba = new byte[fldNameLen];
                dis.read(ba);
                fldName = new String(ba);
                fieldNames[i] = fldName;  // keep this field name in the list
                fldLen = dis.readShort();
                fieldsMap.put(fldName, new Short(fldLen));
                offset += (2 + fldNameLen + 2);

            //System.out.println("fldNameLen="+Integer.toString(fldNameLen));
            //System.out.println("fldName="+fldName);
            //System.out.println("fldLen="+Integer.toString(fldLen));
            //System.out.println("-----");
            }
            dis.close();
            //ras = new RandomAccessFile(DB_FILE, "rw");
            ras = new RandomAccessFile(dbFile, "rw");
            logger.fine("Recordlength : " + recordLength);
            logger.info("DB Initialized.");

        } catch (IOException ioe) {
            throw ioe;
        }
    }

    /**
     * Closes the database.
     */
    public void closeDB() {
        try {
            this.lockRecord(LOCK_WHOLE_TABLE);
            this.ras.close();
            logger.info("Database file is now closed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception in DB.closeDB().", e);
        }
    }

    // 1.6.09 - read recordLength + 1, parseRecord2()
    public synchronized void readAllRecords() throws IOException {
        String rec;
        int recNo = 0;
        byte[] record = new byte[recordLengthWithFlag];
        ras.seek(offset);
        int noBytesRead;
        while ((noBytesRead = ras.read(record)) == recordLengthWithFlag) {
            rec = new String(record);
            if (rec.startsWith(DELETE_FLAG)) {
                logger.fine("rec is deleted" + recNo);
            } else {
                //System.out.print(""+recNo+" ");
                parseRecord(rec);
            }
            recNo++;
        }
    //System.out.println("read "+recNo+" records");
    }

    /**
     * This method takes the record data in String format and returns an array of
     * Strings that corresponds to the fields of the record.
     * @param recorddata
     * @return array of Strings corresponding to the fields.
     */
    private String[] parseRecord(String record) {
        String[] returnValue = new String[fieldNames.length];
        int startind = 1;  // skip delete flag byte
        for (int i = 0; i < fieldNames.length; i++) {
            int fieldlength = ((Short) fieldsMap.get(fieldNames[i])).intValue();
            returnValue[i] = record.substring(startind, startind + fieldlength).trim();
            startind = startind + fieldlength;
        }

        // debug print
        //for (int i=0; i<returnValue.length; i++) {
        //     System.out.print(fieldNames[i]+"="+returnValue[i]+",");
        //}
        //System.out.println();
        return returnValue;
    }

    /* TO DO:
     *  check logic of finding a match b/c if add Freddy, x, y, z to a file already had Fred, a, b, c might throw Duplicate
     *
     */
    public synchronized String[] readRecord(long recNo) throws RecordNotFoundException {

        if (recNo < 0) {
            throw new RecordNotFoundException("No such record : " + recNo);
        }
        try {
            ras.seek(offset + recNo * (recordLengthWithFlag));

            byte[] ba = new byte[recordLengthWithFlag];
            int noofbytesread = ras.read(ba);
            if (noofbytesread < recordLengthWithFlag) {
                throw new RecordNotFoundException("Unable to read full record : " + recNo);
            }
            String rec = new String(ba);
            if (rec.startsWith(DELETE_FLAG)) {
                throw new RecordNotFoundException("Record deleted: " + recNo);
            }
            return parseRecord(rec);
        } catch (IOException e) {
            throw new RecordNotFoundException("Unable to retrieve the record : " + recNo + " : " + e.getMessage());
        }
    }


    /* ---------------------------------------------------------------------------------------------------
     * 12.31.08 - Implement the following functions and then test lock w/ threads.
     */
    public synchronized void updateRecord(long recNo, String[] data, long lockCookie) throws RecordNotFoundException, SecurityException {
        if (recNo < 0) {
            throw new RecordNotFoundException("No such record : " + recNo);
        }
        if (data == null || data.length != fieldNames.length) {
            throw new SecurityException("Invalid Data");
        }
        if (lockCookie < 0) {
            throw new SecurityException("Invalid lock key");
        }
        Long ownerKeyObj = (Long) locker.getOwner(recNo);
        if (ownerKeyObj == null) {
            throw new SecurityException("You have to lock the record first before updating it.");
        }

        if (lockCookie == ownerKeyObj.longValue()) {
            try {
                ras.seek(offset + recNo * recordLengthWithFlag);
                byte[] ba = new byte[recordLengthWithFlag];
                int noofbytesread = ras.read(ba);
                if (noofbytesread < recordLengthWithFlag) {
                    throw new RecordNotFoundException("No such record : " + recNo);
                }
                String rec = new String(ba);
                if (rec.startsWith(DELETE_FLAG)) {
                    throw new RecordNotFoundException("This record has been deleted : " + recNo);
                }
                ras.seek(offset + recNo * recordLengthWithFlag);
                ras.write(getByteArray(data));
            } catch (IOException e) {
                throw new SecurityException("Unable to update the record : " + recNo + " : " + e.getMessage());
            }
        } else {
            throw new SecurityException("You do not own the lock for this record.");
        }
    }

    /**
     * This method prepares a byte[] for writing to the file.
     * @param data
     * @return A byte array of length recordsize containing the record data.
     * @throws IOException
     */
    private byte[] getByteArray(String[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        String deleteFlag = "  ";
        char[] ca = new char[1];
        deleteFlag.getChars(0, 1, ca, 0);
        dos.write(ca[0]);

        for (int i = 0; i < fieldNames.length; i++) {
            String field = data[i].trim();
            short flength = ((Short) fieldsMap.get(fieldNames[i])).shortValue();
            ca = new char[flength];
            if (field.length() > flength) {
                field.getChars(0, flength, ca, 0);
            } else {
                field.getChars(0, field.length(), ca, 0);
            }
            for (int x = 0; x < flength; x++) {
                dos.write(ca[x]);
            }
        }

        dos.flush();
        dos.close();
        byte[] ba = baos.toByteArray();
        return ba;
    }

    public synchronized void deleteRecord(long recNo, long lockCookie) throws RecordNotFoundException, SecurityException {
        if (recNo < 0) {
            throw new RecordNotFoundException("No such record : " + recNo);
        }
        if (lockCookie < 0) {
            throw new SecurityException("Invalid lock key");
        }
        Long ownerKeyObj = (Long) locker.getOwner(recNo);
        if (ownerKeyObj == null) {
            throw new SecurityException("You have to lock the record first before updating it.");
        }

        if (lockCookie == ownerKeyObj.longValue()) {
            try {
                ras.seek(offset + recNo * recordLengthWithFlag);
                byte[] ba = new byte[recordLengthWithFlag];
                int noofbytesread = ras.read(ba);
                if (noofbytesread < recordLengthWithFlag) {
                    throw new RecordNotFoundException("No such record : " + recNo);
                }
                String rec = new String(ba);
                if (rec.startsWith(DELETE_FLAG)) {
                    throw new RecordNotFoundException("This record has already been deleted : " + recNo);
                }
                ras.seek(offset + recNo * recordLengthWithFlag);
                ras.write(DELETE_FLAG.getBytes());
                locker.unlock(recNo, lockCookie);
            } catch (IOException e) {
                throw new SecurityException("Unable to delete the record : " + recNo + " : " + e.getMessage());
            }
        }
    } // end delete

    /**
     * Find all records that match the specified criteria
     * using a sequential search
     * @param criteria - The target criteria to look for.
     * criteria array contains the same number of fields as in the database record.
     * A null value in criteria[n] matches any value of the correspondent field n-th.
     *A non-null  value in criteria[n] matches any field value that begins with criteria[n].
     * (For example, "Fred" matches "Fred" or "Freddy".)
     * @return an array of record numbers that match the specified criteria. Null if nothing found or empty array if criteria is invalid
     */
    public synchronized long[] findByCriteria(String[] criteria) {
        ArrayList matchingindices = new ArrayList();
        try {
            if (criteria == null || criteria.length != fieldNames.length) {
                return new long[0]; //return empty array if criteria is invalid.
            }
            byte[] ba = new byte[recordLengthWithFlag];
            long recNo = 0;
            String[] fielddata;
            boolean match;
            ras.seek(offset);
            int noBytesRead = 0;
            String rec;
            while ((noBytesRead = ras.read(ba)) == recordLengthWithFlag) {
                rec = new String(ba);
                if (rec.startsWith(DELETE_FLAG)) {
                    recNo++;
                    continue;
                }
                fielddata = parseRecord(rec);
                // search each field x = criteria[x], when criteria[x] is null, it is considered a match
                match = true;
                for (int i = 0; i < fieldNames.length; i++) {
                    if (criteria[i] == null) {
                        continue;
                    }
                    if (fielddata[i].startsWith(criteria[i])) {
                        // matched, do nothing
                    } else {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    //System.out.println("found " + recNo);
                    matchingindices.add(new Long(recNo));
                }
                recNo++;
            }
            // reach end of file, create and return found records
            int noofmatches = matchingindices.size();
            if (noofmatches > 0) {
                long[] retvalue = new long[noofmatches];
                for (int i = 0; i < noofmatches; i++) {
                    retvalue[i] = ((Long) matchingindices.get(i)).longValue();
                }
                return retvalue;
            }
        } catch (Exception e) {
            // do nothing.
        }
        return null;
    }

    public synchronized long createRecord(String[] data) throws DuplicateKeyException {
        if (data == null || data.length != fieldNames.length) {
            throw new DuplicateKeyException("Invalid data");
        }
        long[] existingRecNos = findByCriteria(data);
        if ((existingRecNos != null) && (existingRecNos.length > 0)) {
            throw new DuplicateKeyException("A record with given data already exists.");
        }
        try {
            int availRecNo = getFirstDeletedRecordNo();
            ras.seek(offset + availRecNo * recordLengthWithFlag);
            ras.write(getByteArray(data));
            return availRecNo;
        } catch (Exception e) {
            throw new DuplicateKeyException("Unable to create new record : " + e.getMessage());
        }
    }

    /**
     * Searches the file to find a slot that contains a deleted record. If none is available, it returns the last+1 record number.
     * @return record number of first available deleted record.
     */
    private int getFirstDeletedRecordNo() {
        try {
            int retval = 0;
            ras.seek(offset);
            byte[] ba = new byte[recordLengthWithFlag];
            int noofbytesread = 0;
            while ((noofbytesread = ras.read(ba)) == recordLengthWithFlag) {
                String rec = new String(ba);
                if (rec.startsWith(DELETE_FLAG)) {
                    return retval;
                }
                retval++;
                ba = new byte[recordLengthWithFlag];
            }
            return retval;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Releases the lock on a record. Cookie
     * @param recNo - The record number to be unlocked.
     * @param lockCookie - The cookie returned when the record was locked;
     * otherwise throws SecurityException.
     *
     * Unlocks the record.
     * @param recNo Record to be unlocked. -1 implies unlock the database.
     * @param lockCookie The record (or the database) should have been locked using this key.
     * @throws java.lang.SecurityException Thrown if the lockCookie does not match the key with which the record was locked.
     */
    public void unlock(long recNo, long cookie) throws SecurityException {
        locker.unlock(recNo, cookie);
    }

    /**
     * Implement DBAccess interface function.
     * Locks a record so that it can only be updated or deleted by this client.
     * If the specified record is already locked by a different client,
     * the current thread gives up the CPU until the record is unlocked.
     * @param recNo - The record number to be locked.
     *
     * @return the lock identification or cookie that must be used
     * when the record is unlocked, updated, or deleted.
     */
    public synchronized long lockRecord(long recNo) throws RecordNotFoundException {
        if (recNo < LOCK_WHOLE_TABLE) {
            throw new RecordNotFoundException("No such record : " + recNo);
        }
        if (recNo == LOCK_WHOLE_TABLE) {
            return locker.lock(LOCK_WHOLE_TABLE);
        }

        if (recNo < 0) {
            throw new RecordNotFoundException("No such record : " + recNo);
        }
        try {
            ras.seek(offset + recNo * recordLengthWithFlag);
            byte[] ba = new byte[recordLengthWithFlag]; // +1 because first byte is delete flag
            //System.out.println("try to read " + recordLengthWithFlag + " bytes");
            int noofbytesread = ras.read(ba);
            if (noofbytesread < recordLengthWithFlag) {
                throw new RecordNotFoundException("No such record/Insufficient data : " + recNo);
            }

            String rec = new String(ba);
            if (rec.startsWith(DELETE_FLAG)) {
                throw new RecordNotFoundException("This record has been deleted : " + recNo);
            }
        } catch (IOException e) {
            throw new RecordNotFoundException("Unable to retrieve the record : " + recNo + " : " + e.getMessage());
        }
        return locker.lock(recNo);
    }

    // 12.28.08 - for testing ======================================================================
    public static void main(String args[]) {
        String[] ary;
        try {
            DB mydb = new DB(DB_FILE, 257);

            mydb.readAllRecords();
            /*
            String[] criteria = {"Cast","","","Y","","2",""};
            long[] res = mydb.findByCriteria(criteria);
            if ((res != null) && (res.length>0)) {
            for (int i=0;i<res.length;i++) {
            System.out.println("rec "+res[i]);
            }
            }

            lk = dbi.lock(8);
            r = dbi.read(8);
            r[3] = "45";
            r[4] = "4.5";
            dbi.update(8, r, lk);
             */

            /* test insert
            String[] sary = { "MyHotel2", "MyLoc", "50", "Y", "$6789", "2009/01/13","Anh"};
            long newrecno = mydb.createRecord(sary);
            System.out.println("new recno="+newrecno);
            String[] s = mydb.readRecord(newrecno);
             */

            /* test delete
            long key = mydb.lockRecord(31);
            mydb.deleteRecord(31,key);
            System.out.println("first del recno="+mydb.getFirstDeletedRecordNo());
             */

            // test update
            long x = 30;
            long key = mydb.lockRecord(x);
            String[] sary = mydb.readRecord(x);
            sary[0] = "Hotel California";
            sary[1] = "Hollywood";
            sary[2] = "3";
            sary[3] = "N";
            sary[4] = "$1001";
            sary[5] = "2009/01/14";
            mydb.updateRecord(x, sary, key);

        //System.out.println();
        } catch (RecordNotFoundException rnfe) {
            rnfe.printStackTrace();
//    	} catch (DuplicateKeyException dke) {
//    		dke.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end main()

    /**
     * This main method is just to test the locking functionality.
     * It creates 4 threads that randomly request and release locks.
     * @param args
     * @throws java.lang.Exception
     */
    public static void main2(String[] args) throws Exception {
        DB dbi = new DB(DB_FILE, 257);
        LockTestThread t1 = new LockTestThread("T1", dbi);
        t1.start();

        LockTestThread t2 = new LockTestThread("T2", dbi);
        t2.start();

        LockTestThread t3 = new LockTestThread("T3", dbi);
        t3.start();

        LockTestThread t4 = new LockTestThread("T4", dbi);
        t4.start();

    }

    /**
     * This Inner class implements a primitive lock manager. It keeps a hashtable that stores the record number as key and
     * an Object that denotes that somebody is using the record.
     */
    private class LockManager {

        private Hashtable ht = new Hashtable();
        boolean dblocked = false;
        Long dbkey = null;

        public Long getOwner(long recordNo) {
            return ((Long) ht.get("" + recordNo));
        }

        /**
         * This method removes the value stored in the hashtable for recordNo.
         * and notifys all the threads waiting on 'this'.
         *
         * @param int recordNo The record no. to be unlocked.
         */
        public synchronized void unlock(long recordNo, long lockCookie) throws SecurityException {
            // case unlock table...
            if (recordNo == LOCK_WHOLE_TABLE) {
                if (lockCookie == dbkey.longValue()) {
                    dblocked = false;
                    notifyAll();
                    return;
                } else {
                    throw new SecurityException("You don't own DB Lock");
                }
            }

            // case unlock a record
            Long key = (Long) ht.get("" + recordNo);

            if ((key != null) && (lockCookie == key.longValue())) {
                ht.remove("" + recordNo);
                notifyAll();
            } else {
                throw new SecurityException("You don't own lock for this record : " + recordNo);
            }
        }

        /**
         * This method waits till all the locks(entries in the hashtable) are released.
         * It then sets the dblocked flag to true and returns a key.
         * It means this table is locked and ready for user to lock individual records.
         */
        private synchronized long lockDB() {
            while (dblocked || ht.size() != 0) {
                try {
                    logger.fine("dblock waiting... dblocked flag = " + dblocked + " locker size = " + ht.size());
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logger.fine("dblocked flag = " + dblocked + " Locker size = " + ht.size());
            dblocked = true;
            dbkey = new java.util.Date().getTime();
            return dbkey;
        }

        /**
         * This method waits till the hashtable returns null for the requested record no.
         * Then it puts a key for that record number and return that key.
         *
         * @param int recordNo The record number to be locked.
         * @return The key. This key should be used to unlock or update/delete methods.
         */
        public synchronized long lock(long recordNo) {
            if (recordNo == LOCK_WHOLE_TABLE) {
                return lockDB(); // what does this do?
            }

            Long key = (Long) ht.get("" + recordNo);
            // ? recordNo not found, recordNo found but no value? what is dblocked = true?
            if (key == null && !dblocked) {
                key = new java.util.Date().getTime();
                ht.put("" + recordNo, key);  // lock it
                return key;
            } else {
                while (ht.get("" + recordNo) != null || dblocked) {  // someone else has a lock on this record or table is locked.
                    try {
                        wait();
                    } catch (Exception e) {
                        logger.fine("Some Exception in waiting for record :" + recordNo);
                    }
                }
                // get here when ht.get(recordNo) is not null and dblocked is false
                key = new java.util.Date().getTime();
                ht.put("" + recordNo, key);  // lock it
                return key;
            }
        }
    }

    /**
     * This class is just to test the locking functionality.
     * It implements a thread the continuously acquires and releases locks on
     * random record numbers.
     */
    public static class LockTestThread extends Thread {

        private String name = "";
        private DB dbi;

        public LockTestThread(String name, DB db) {
            LockTestThread.this.name = name;
            LockTestThread.this.dbi = db;
        }

        public void run() {
            try {
                while (true) {
                    long recno = (long) (Math.random() * 10) - 1; //substract 1 so that recno can also be -1 for database lock.
                    int sleeptime1 = (int) (Math.random() * 10);
                    int sleeptime2 = (int) (Math.random() * 10);
                    if (recno == -1) {
                        sleeptime2 = 5;
                    }
                    //System.out.println(name + " Sleeping : " + sleeptime1+"   "+sleeptime2);
                    Thread.sleep(sleeptime1 * 1000);
                    long tm1 = System.currentTimeMillis();
                    long key = dbi.lockRecord(recno);
                    long tm2 = System.currentTimeMillis();
                    System.out.println(name + " got lock for : " + recno + " in " + (tm2 - tm1) + " millis.");
                    Thread.sleep(sleeptime2 * 1000);
                    //System.out.println(name + " unlocking DB after " + sleeptime2 + " secs.");
                    dbi.unlock(recno, key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } // end class LockTestThread
} // end class DB

/**
	Data file Format
The format of data in the database file is as follows:

Start of file
4 byte numeric, magic cookie value. Identifies this as a data file
4 byte numeric, total overall length in bytes of each record
2 byte numeric, number of fields in each record

Schema description section.
Repeated for each field in a record:
2 byte numeric, length in bytes of field name
n bytes (defined by previous entry), field name
2 byte numeric, field length in bytes
end of repeating block

Data section.
Repeat to end of file:
1 byte "deleted" flag. 0 implies valid record, 1 implies deleted record
Record containing fields in order specified in schema section, no separators between fields, each field fixed length at maximum specified in schema information

End of file

All numeric values are stored in the header information use the formats of the DataInputStream and DataOutputStream classes. All text values, and all fields (which are text only), contain only 8 bit characters, null terminated if less than the maximum length for the field. The character encoding is 8 bit US ASCII.
Database schema
The database that URLyBird uses contains the following fields:
Field descriptive name 	Database field name 	Field length 	Detailed description
Hotel Name 	name 	64 	The name of the hotel this vacancy record relates to
City 	location 	64 	The location of this hotel
Maximum occupancy of this room 	size 	4 	The maximum number of people permitted in this room, not including infants
Is the room smoking or non-smoking 	smoking 	1 	Flag indicating if smoking is permitted. Valid values are "Y" indicating a smoking room, and "N" indicating a non-smoking room
Price per night 	rate 	8 	Charge per night for the room. This field includes the currency symbol
Date available 	date 	10 	The single night to which this record relates, format is yyyy/mm/dd.
Customer holding this record 	owner 	8 	The id value (an 8 digit number) of the customer who has booked this. Note that for this application, you should assume that customers and CSRs know their customer ids. The system you are writing does not interact with these numbers, rather it simply records them. If this field is all blanks, the record is available for sale.}
*/