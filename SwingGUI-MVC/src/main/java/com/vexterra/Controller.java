package com.vexterra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.vexterra.View;
import com.vexterra.Model;

public class Controller implements ActionListener {
	private View v = null;
	private Model m = null;

    // This is used for both network and standalone
    private boolean serverStarted = false;
    private String dbFileName,  host,  port;
    private String localDbFileName;
    private int localDbMagicCode;
    private String curQuery = null;
    private String curName = null;
    private String curLocation = null;
    private boolean localFlag = false;
	/**
	 * TODO: Add code to use a database This constructor takes a ClientFrame.
	 * .It creates an object of class ClientModel, which contains the data
	 * needed by the ClientFrame to display to the user. .It sets the client
	 * frame as an observer to this model and notifies it.
	 *
	 */
	public Controller(View cv) {
		try {
			v = cv;
			m = new Model();
			v.setController(this); // send this controller to the View
			v.setModel(m); // send this new model to the View
			m.notifyObservers(new Boolean(true));
			v.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});
			// TODO: readInitProperties();
		} catch (Exception e) { // catch RemoteException, CheckedException and
								// anything else
			JOptionPane.showMessageDialog(v, "Unable to connect to the server: " + e.getMessage(), "Error Message",
					JOptionPane.ERROR_MESSAGE);
			// TODO: logger.log(Level.SEVERE, "Exception in connecting to server :"+e.getMessage(), e);
		}
	}

    
	/* TODO: read all application properties here...
	   private void readInitProperties() throws FileNotFoundException, IOException {
	        Properties appProps = new Properties();
	        FileInputStream in = new FileInputStream("application.properties");
	        appProps.load(in);
	        in.close();
	        this.localDbFileName = appProps.getProperty("client.localdbfile");
	        this.dbFileName = appProps.getProperty("server.dbfile");
	        this.host = appProps.getProperty("client.serverhost");
	        this.port = appProps.getProperty("client.serverport");
	    }
	    */

	    /**
	     * All the action events in the application GUI, upon which some action has to be taken, 
	     * data has to be changed, or the view has to be changed, come to this method.
	     * It identifies the action and delegates the control to appropriate doXXX method.
	     *
	     * @param ae The action event. ActionCommand identifies the action.
	     */
	    public void actionPerformed(ActionEvent ae) {
	        String action = ae.getActionCommand();
	        //System.out.println("In  Controller actionPerformed "+action);
	        if (action.startsWith("SEARCH_NAME_LOCATION")) {
	            String[] params = action.split(",");
	            //System.out.println("Search..." + params[1]+","+params[2]);
	            switch (params.length) {
	                case 1:  // no search criteria = view all
	                    curQuery = "viewall";
	                    curName = null;
	                    curLocation = null;
	                    refreshView(curQuery, curName, curLocation);
	                case 2:
	                    curQuery = "viewbynamelocation";
	                    curName = params[1];
	                    curLocation = null;
	                    refreshView(curQuery, curName, curLocation);
	                    break;
	                case 3:
	                    curQuery = "viewbynamelocation";
	                    curName = params[1];
	                    curLocation = params[2];
	                    refreshView(curQuery, curName, curLocation);
	                    break;
	                default:
	                    break;
	            }
	        } else if ("BOOK_ROOM".equals(action)) {
	            //System.out.println("Book...");
	            reserveRoom(action);
	        } else if ("VIEW_ALL".equals(action)) {
	            //System.out.println("View all...");
	            curQuery = "viewall";
	            curName = null;
	            curLocation = null;
	            refreshView(curQuery, curName, curLocation);
	        } else if ("CLEAR".equals(action)) {
	            //System.out.println("Clear...");
	            refreshView("clear", "", "");
	        }
	    }

	    /**
	     * This is just a helper method that calls appropriate searchXXX method.
	     * @param query
	     * @param maxguests
	     * @param location
	     */
	    private void refreshView(String query, String name, String location) {
            JOptionPane.showMessageDialog(v, "TODO", query + "," + name + "," + location, JOptionPane.INFORMATION_MESSAGE);
	    }

	    /**
	     * Books a room.
	     * .It identifies the room by looking at the row no. attached to the action parameter ( BOOK_ROOM,12, where 12 is the row index).
	     * If the action parameter does not have the row no., use the table selected index
	     * .It calls the reserveRoom method on the server.
	     * .It pops up appropriate messages if something is wrong.
	     * .Finally, it refreshes the display.
	     *
	     * @param action The the complete action command that has row index attached to it.
	     */
	    public void reserveRoom(String action) {
	        int index = -1;
	        String[] data = null;
	        String[] ary = action.split(",");
	        if (ary.length > 1) {
	            index = Integer.parseInt(ary[1]);
	            data = m.getAllRows()[index];
	        } else {
	            index = v.getTablePanel().getSelectedIndex();
	            if (index < 0) {
	                JOptionPane.showMessageDialog(v, "Please select a record before click Reserve/Book.", "Reserve Room", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	            data = m.getAllRows()[index];
	        }

	        String customerName = JOptionPane.showInputDialog(v, "Please enter the customer name :", "Reserve Room", JOptionPane.INFORMATION_MESSAGE);
	        if (customerName != null) {
	        	/* TODO: your code here...
	            try {
	                // TODO: use the data[] to search for that record...
	                boolean status = curServer.reserveRoom(customerName, index, data);
	                if (status) {
	                    JOptionPane.showMessageDialog(v, "Room " + data[0] + " has been reserved for " + customerName, "Reserve Room", JOptionPane.INFORMATION_MESSAGE);
	                    // refresh current table view from the last search/viewall click
	                    refreshView(curQuery, curName, curLocation);
	                } else {
	                    JOptionPane.showMessageDialog(v, "Unable to reserve the room", "Reserve Room", JOptionPane.INFORMATION_MESSAGE);
	                }
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(v, "Unable to reserve the room " + e.getMessage(), "Reserve Room Error", JOptionPane.INFORMATION_MESSAGE);
	            } */
	        }
	    }	
}
