 package com.kyron.rmi;

/*import com.unitedcaterers.UCServerInterface;
import com.unitedcaterers.util.CheckedException;
import com.unitedcaterers.db.*;
 */
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBServer implements DBServerInterface {
    //protected static Logger logger = Logger.getLogger("com.unitedcaterers.server.UCServer");

    private DBAccess db;
    private boolean closedflag = false;

    public DBServer(String dbfilename, int magiccode) throws RemoteException, CheckedException {
        try {
            db = new DB(dbfilename, magiccode);
        } catch (IOException ioe) {
            throw new CheckedException("Unable to create DB: " + ioe.getMessage());
        }
    }

    public String[][] searchByName(String name) throws RemoteException, CheckedException {
        String[] criteria = new String[]{name, null, null, null, null, null, null};
        return findAndReturnData(criteria);
    }

    public String[][] searchByLocation(String location) throws RemoteException, CheckedException {
        String[] criteria = new String[]{null, location, null, null, null, null, null};
        return findAndReturnData(criteria);
    }

    public String[][] searchByNameAndLocation(String name, String location) throws RemoteException, CheckedException {
        String[] criteria = new String[]{name, location, null, null, null, null, null};
        return findAndReturnData(criteria);
    }

    public String[][] getAllRecords() throws RemoteException, CheckedException {
        String[] criteria = new String[]{null, null, null, null, null, null, null};
        return findAndReturnData(criteria);
    }

    public boolean reserveRoom(String customerid, int recordNo, String[] originalData) throws RemoteException, CheckedException {
        if (closedflag) {
            throw new CheckedException("Database connection has been closed.");
        }
        boolean status = false;
        long lockkey = -1;
        //long recordNo = -1;
        final int COL_OWNER = 6;
        final int COL_RECID = 7;
        try {
            recordNo = Integer.parseInt(originalData[COL_RECID]); // this is why we need rec id in the last column....

            System.out.println("DBServer.reserveRoom(recordno)=" + recordNo);

            lockkey = db.lockRecord(recordNo);
            String[] data = db.readRecord(recordNo);

            // This room is available to book when there is no owner and data is not changed.
            if (data[COL_OWNER] == null || data[COL_OWNER].trim().length() == 0) {
                boolean datachanged = false;
                for (int n = 0; n < originalData.length - 1; n++) { // don't check the id column because data doesn't have it
                    if (!originalData[n].trim().equals(data[n])) {
                        datachanged = true;
                        break;
                    }
                }
                if (datachanged) {
                    throw new CheckedException("Please refresh your view to see update data...");
                }
                data[COL_OWNER] = customerid;  // book this room
                db.updateRecord(recordNo, data, lockkey);
                status = true;
            } else if (data[COL_OWNER].trim().equals(customerid)) {
                // This room is already booked??
                status = true;
            } else {
                throw new CheckedException("This room is not available.");
            }
            return status;
        } catch (RecordNotFoundException re) {
            throw new CheckedException("Unable to reserve the room: Room does not exist.");
        } catch (SecurityException se) {
            throw new CheckedException("Unable to reserve the room: Security error.");
        } finally {
            try {
                db.unlock(recordNo, lockkey);
            } catch (Exception e) {
                //logger.log(Level.SEVERE, "Serious problem in unlocking. Should not happen.", e);
            }
        }
    }

    /**
     * This method is used by the local clients so that when the client exits, it can close the database file cleanly.
     */
    public void close() {
        ((DB) db).closeDB();
    }

    /**
     * This is an additional method (not specified in the DataBase interface) that
     * is very useful for the application. It returns the actual data int String[][]
     * instead of just the record numbers that match the given criteria. The last
     * value of the String[] is the record number the rest are the fields.
     * @param criteria A string array of same length as the number of fields.
     * @return An array of integers containing record numbers.
     */
    public String[][] findAndReturnData(String[] criteria) throws CheckedException {
        long lockkey = DB.NO_LOCK;
        if (closedflag) {
            throw new CheckedException("Database connection has been closed.");
        }
        String[][] retval = null;
        try {
            // Lock the whole table because some of them might be deleted/modified during the fetch
            lockkey = db.lockRecord(DB.LOCK_WHOLE_TABLE);
            long[] idsAry = db.findByCriteria(criteria);
            if (idsAry != null) {
                retval = new String[idsAry.length][];
                for (int i = 0; i < idsAry.length; i++) { // all rows

                    String[] data = db.readRecord(idsAry[i]);

                    //System.out.println("findAndReturnData(criteria) recno " + idsAry[i]);
                    retval[i] = new String[data.length + 1];
                    for (int j = 0; j < data.length; j++) {
                        retval[i][j] = data[j];
                    }
                    retval[i][data.length] = "" + idsAry[i]; // last item is the id
                }
            }
            db.unlock(DB.LOCK_WHOLE_TABLE, lockkey);
        } catch (RecordNotFoundException rnfe)    {
            throw new CheckedException("Record not found. " + rnfe.getMessage());
        } catch (Exception e) {
            //logger.log(Level.SEVERE, "Exception in findAndReturnData()", e);
        }
        return retval;
    }
}
