 package com.kyron.rmi;
//import DBServerInterface;
//import CheckedException;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

/**
 * Since we need to make this a RMI Remote object we have to extend it from UnicastRemoteObject and
 * so we cannot extend it from DBServer. Instead, we will instantiate DBServer object and delegate all the calls to it.
 * In affect, it is exactly same as DBServer except that it is a remote class.
 */

public class RMIServer extends UnicastRemoteObject implements DBServerInterface {
    private DBServer dbs;
    
    public RMIServer(String dbfilename, String magiccode) throws RemoteException, CheckedException {
    	try {
        	int code = Integer.valueOf(magiccode);
	        dbs = new DBServer(dbfilename, code);
    	} catch (Exception e) {
    		throw new CheckedException("Unable to create DBServer: " + e.getMessage());
    	}

    }
    
    public String[][] searchByName(String name) throws RemoteException, CheckedException {
    	return dbs.searchByName(name);
    }
    
    public String[][] searchByLocation(String location) throws RemoteException, CheckedException {
        return dbs.searchByLocation(location);
    }
    
    public String[][] searchByNameAndLocation(String name, String location) throws RemoteException, CheckedException {
    	return dbs.searchByNameAndLocation(name, location);
    }
        
    public String[][] getAllRecords() throws RemoteException, CheckedException {
    	return dbs.getAllRecords();
    }
    
    public boolean reserveRoom(String customerId, int recId, String[] originalData) throws RemoteException, CheckedException {
    	return dbs.reserveRoom(customerId, recId, originalData);
    }
   
}
