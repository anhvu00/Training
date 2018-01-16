package com.vexterra;

import com.vexterra.Controller;
import com.vexterra.View;

public class Client {
	/**
	 * Start up the client GUI
	 */
    public static void startUp()    {
       View v = new View();
        Controller c = new Controller(v);
        v.setSize(700,400);
        v.setLocationRelativeTo(null); // makes sure that the frame is centered on the screen.
        v.setVisible(true);
    }
}
