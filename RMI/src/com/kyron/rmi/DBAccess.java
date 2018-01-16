
/**
 * This is the database interface given in the requirements of the assignment.
 * @(#)DBAccess.java
 * @author Anh Vu
 * @version 1.0 2008/6/26
 */
  package com.kyron.rmi;
public interface DBAccess
{
    /**
     * Reads a record from the file.
     * @param recNo - The record number to be read.
     * @return an array where each element is a record value.
     */
	public String [] readRecord(long recNo)
	throws RecordNotFoundException;
	
	/**
	 * Modifies the fields of a record. Throws SecurityException
	 * if the record is locked with a cookie other than lockCookie.
	 * @param recNo - The record number to be updated.
	 * @param data - The new value for field n appears in data[n]
	 * @param lockCookie - The lock identification.
	 */
	public void updateRecord(long recNo, String[] data, long lockCookie)
	throws RecordNotFoundException, SecurityException;
	
	/** 
	 * Deletes a record, making the record number and associated disk
	 * storage available for reuse. 
	 * Throws SecurityException if the record is locked with a cookie
	 * other than lockCookie.
	 * @param recNo - The record number to be deleted.
	 * @param lockCookie - The lock identification.
	 */
	public void deleteRecord(long recNo, long lockCookie)
	throws RecordNotFoundException, SecurityException;
	
	/**
	 * Find all records that match the specified criteria.
	 * @param criteria - The target criteria to look for.  
	 * Field n in the database file is described by
	 * criteria[n]. A null value in criteria[n] matches any field
	 * value. A non-null  value in criteria[n] matches any field
	 * value that begins with criteria[n]. (For example, "Fred"
	 * matches "Fred" or "Freddy".)
	 * @return an array of record numbers that match the specified
	 * criteria.
	 */
	public long[] findByCriteria(String[] criteria);

	/**
	 * Creates a new record in the database (possibly reusing a
	 * deleted entry).
	 * @param data - The new value for field n appears in data[n]
	 * @return the record number of the new record after insert the 
	 * given data.
	 */
	public long createRecord(String [] data)
	throws DuplicateKeyException;
	
	/**
	 * Locks a record so that it can only be updated or deleted 
	 * by this client. If the specified record is already locked 
	 * by a different client, the current thread gives up the CPU 
	 * and consumes no CPU cycles until the record is unlocked.
	 * @param recNo - The record number to be locked.
	 * @return the lock identification or cookie that must be used 
	 * when the record is unlocked, updated, or deleted. 
	 */
	public long lockRecord(long recNo)
	throws RecordNotFoundException;
	
	/**
	 * Releases the lock on a record. Cookie 
	 * @param recNo - The record number to be unlocked.
	 * @param lockCookie - The cookie returned when the record was locked; 
	 * otherwise throws SecurityException.
	 */
	public void unlock(long recNo, long cookie)
	throws SecurityException;
}