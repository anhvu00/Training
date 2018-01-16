 package com.kyron.rmi;
/**
 * Starts the client side of the application. In a nutshell, it instantiates and
 * ties the ClientController and ClientFrame and sets the ClientFrame visible.
 */
public class Client
{
    /**
     *Starts up the client.
     *@param clienttype Specifies whether we want to use RMI or Socket implementation. The value must be "rmi" or "socket" or "none". If the value of "none" is given, a local database is used directly.
     */
    public static void startup(String clienttype)
    {
       View v = new View();
        Controller c = new Controller(v, clienttype);
        v.setSize(700,400);
        v.setLocationRelativeTo(null); // makes sure that the frame is centered on the screen.
        v.setVisible(true);
    }

}
