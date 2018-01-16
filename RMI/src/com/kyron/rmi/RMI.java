/**
 * @(#)RMI.java
 *
 * JFC RMI application
 *
 * @author 
 * @version 1.00 2009/4/20
 * This class is the entry point to the application. It determines what - the client
 * or the server, needs to be run and then starts up the required component.
 * java ApplicationMain <param> where <param> can be:
 * 1. SERVER = start the server and listen for connection
 * 2. CLIENT = start a client to send requests to server.
 * 3. STANDALONE = a stand alone client 
 * @author Enthuware
 * @version 1.0
 */
 package com.kyron.rmi;
 
 import java.io.*;
 import java.util.*;
 
public class RMI {
	
  public static void main(String[] args) throws Exception  {
    String currentdir = new File(".").getCanonicalPath();
    
    if(args.length == 0)    {
      System.out.println("Usage: ApplicationMain <param>");
      System.out.println("<param> can be: SERVER, CLIENT, or STANDALONE.");
    }
    else if("CLIENT".equalsIgnoreCase(args[0]))    {
      Client.startup(args[0]);
    }
    else if("STANDALONE".equalsIgnoreCase(args[0]))    {
      Client.startup(args[0]);
    }
    else if("SERVER".equalsIgnoreCase(args[0]))  {
      Server.startup();
    }
    else    {
      System.out.println("Invalid parameter.");
      System.out.println("Usage: ApplicationMain <param>");
      System.out.println("<param> can be: SERVER, CLIENT, or STANDALONE.");
    }
  }
  
} // end RMI / ApplicationMain