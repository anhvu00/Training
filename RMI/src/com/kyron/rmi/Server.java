 package com.kyron.rmi;
import java.rmi.*;
import java.io.*;
import java.util.*;
import javax.swing.*;  // JOptionPane
import java.util.logging.Logger;

/**
 * This class provides the startup method for the RMI Server application.
 * It startsup the registry, instantiates the remote server object and
 * binds it to /RemoteRMIServerByAnhVu name.
 * @author Enthuware
 * @version 1.0
 */
public class Server {
    //private static Logger logger = Logger.getLogger("com.unitedcaterers.server.RMIServerMain");

    private static String dbFileName;
    private static int dbMagicCode,  port;

    public static void startup() {
        String name = "/RemoteRMIServerByAnhVu";
        boolean retryflag = true;

        while (retryflag) {
            try {
                //readInitProperties();
                readConnectionInfo();

                //This starts up the registry at port 1099 on the local machine.
                java.rmi.registry.Registry regs = java.rmi.registry.LocateRegistry.createRegistry(port);
                //logger.fine("RMI registry created on port :"+String.valueOf(port));
                name = "rmi://localhost:" + String.valueOf(port).trim() + "/RemoteRMIServerByAnhVu";
                System.out.println("Server startup() " + name);
                //logger.fine("Creating RMIServer with arguments: "+dbFileName+" , "+String.valueOf(dbMagicCode)+"  by name : "+name);

                //Create the remote object
                RMIServer theserver = new RMIServer(dbFileName, String.valueOf(dbMagicCode));

                //Bind the remote object
                //System.out.println("before bind");
                Naming.rebind(name, theserver);
                //logger.fine("RMIServer bound.");
                JOptionPane.showMessageDialog(null, "RemoteRMIServerByAnhVu is now bound and available for clients.");
                retryflag = false;
            } catch (Exception e) {
                System.out.println("Unable to start RMI Server: " + e.getMessage());
                e.printStackTrace();
                int choice = JOptionPane.showConfirmDialog(null, "Unable to start RMI Server: " + e.getMessage() + ". Do you want to try again?", "Application", JOptionPane.YES_NO_OPTION);
                retryflag = (choice == JOptionPane.YES_OPTION);
                if (!retryflag) {
                    System.exit(0);
                }
            }
        }
    }

    private static void readInitProperties() throws FileNotFoundException, IOException {
        // create and load default properties
        Properties appProps = new Properties();
        FileInputStream in = new FileInputStream("RMI.properties");
        appProps.load(in);
        in.close();
        dbFileName = appProps.getProperty("server.dbfile");
        dbMagicCode = Integer.valueOf(appProps.getProperty("server.dbmagiccode"));
        port = Integer.valueOf(appProps.getProperty("server.port"));
    }

    // read RMI.properties if exist, if not, show dialog, get the info, then save to RMI.properties.
    private static void readConnectionInfo() {
        try {
            Properties appProps = new Properties();
            FileInputStream in = new FileInputStream("RMI.properties");
            appProps.load(in);
            in.close();
            dbFileName = appProps.getProperty("server.dbfile");
            dbMagicCode = Integer.valueOf(appProps.getProperty("server.dbmagiccode"));
            port = Integer.valueOf(appProps.getProperty("server.port"));
            System.out.println("read RMI.properties");

        } catch (Exception e) {
            // use the dialog
            ServerPropertiesDialog spd = new ServerPropertiesDialog(null);
            Properties appProps = spd.loadProperties("RMI.properties");
            if (appProps == null) {
                return; // why return here? what is the connection?
            }
            dbFileName = appProps.getProperty("server.dbfile");
            dbMagicCode = Integer.valueOf(appProps.getProperty("server.dbmagiccode"));
            port = Integer.valueOf(appProps.getProperty("server.port"));
            System.out.println("read user input");
        }
    }
}
