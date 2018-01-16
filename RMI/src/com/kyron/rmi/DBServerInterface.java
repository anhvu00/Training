 package com.kyron.rmi;
 
import java.rmi.RemoteException;

/**
 * This interface captures all the business methods required by the client.
 * The networked (RMI/Socket based) and non-networked servers, both, implement this interface.
 * The benefit of this approach is that the client can instantiate either a networked server
 * or a non-networked server without having any special code for either of them.
 *
 * Since a server can be either networked or non-networked, all methods throw RemoteException besides
 * throwing a generic CheckedException, which is an application exception. The implementation of the methods
 * for the non-networked server will only throw CheckedException, while the implementation of the methods
 * for the networked server will wrap the CheckedException into RemoteException. The clients should be prepared
 * to handle both the exceptions.
 * */

// TO DO: see assignment for what type of search required....
public interface DBServerInterface extends java.rmi.Remote {
  String[][] searchByName(String name) throws RemoteException, CheckedException;
  String[][] searchByLocation(String location) throws RemoteException, CheckedException;
  String[][] searchByNameAndLocation(String name, String location) throws RemoteException, CheckedException;
  String[][] getAllRecords() throws RemoteException, CheckedException;
  boolean reserveRoom(String customerid, int recordNo, String[] originalData) throws RemoteException, CheckedException;
}

