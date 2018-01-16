 package com.kyron.rmi;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a helper class that implements most of the logic to capture the
 * properties to run the server or the client through a GUI.
 * The subclass will have to implement initControls() method, in which it can build the GUI with any number of text field, checkboxes etc. It should have OK and Cancel Buttons.
 * To use it, just instantiate the subclass and call showDialog(). This call blocks until the user clicks OK or Cancel. The values entered in the input fields are stored in properties object, which can be accessed using getProperties() method.
 *
 * @author Enthuware
 * @version 1.0
 */
public abstract class BasePropertiesDialog extends JDialog {
    
    protected static Logger logger = Logger.getLogger("com.unitedcaterers.db.BasePropertiesDialog");
    /**
     *Used to check whether the user clicked OK, Cancel.
     *Method showDialog() returns this value when the Dialog is closed.
     */
    protected int status = -1;
    
    /**
     *This value is returned when the user clicks OK button.
     */
    public static final int OK = 0;
    
    /**
     *This value is returned when the user clicks Cancel button or closes the dialog using Window close event.
     */    
    public static final int CANCEL = -1;
    
    /**
     *Stores the properties (name-value pairs) that are to be captured from the user through this dialog.
     */
    private Properties properties;
    
    public BasePropertiesDialog(JFrame parent) {
        super(parent);
        this.setTitle("Database Connection Properties");
        this.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                BasePropertiesDialog.this.status = CANCEL;
                synchronized(BasePropertiesDialog.this) {
                    BasePropertiesDialog.this.notifyAll();
                }
            }
        }
        );
    }
    
    /**
     *This method must be implemented by the subclass in which it should build the GUI.
     */
    public abstract void initControls();
    
    /**
     *This is a blocking call that makes the dialog visible and waits until the user clicks OK or Cancel.
     *Thus, the subclass, in the actionlisteners of OK and Cancel, should call this.notifyAll() after reading the values from input fields.
     */
    public int showDialog() {
        initControls();
        this.setLocationRelativeTo(null); // makes sure that the dialog is centered on the screen.
        this.setVisible(true);
        synchronized(this) {
            try {
                this.wait();
            } catch(Exception e) {
                //should not happen
                e.printStackTrace();
            }
        }
        this.setVisible(false);
        return status;
    }
    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    
    /**
     *Reads a property file and loads the properties object. It then calls showDialog() to display the GUI. Values in this properties object are used as default values in the input fields. 
     *If the user clicks OK, the new values are stored back into the file.
     *Instead of using showDialog() directly, this method can be used to have the additional functionality of loading the properties from a file and saving them back in the same file.
     */
    public Properties loadProperties(String fileName) {
        Properties props = new Properties();
        File f = null;
        try {
            f = new File(fileName); 
            FileInputStream fis = new FileInputStream(f);
            props.load(fis);
            fis.close();
            logger.fine("Properties loaded from : " + f.getCanonicalPath());
            System.out.println("Load prop from " + f.getCanonicalPath());
        } catch(Exception e) {
            try {
                logger.log(Level.WARNING, "Unable to load properties from: " + f.getCanonicalPath(), e);
            } catch(IOException ioe) {
                logger.log(Level.WARNING, "Unable to load load properties." + ioe.getMessage(), ioe);
            }
        }
        
        this.setProperties(props);
        int status = this.showDialog();
        if(status == BasePropertiesDialog.OK) {
            try {
                f = new File(fileName); 
                FileOutputStream fos = new FileOutputStream(f);
                props.store(fos, "Properties captured by BasePropertiesDialog : "+this.getClass().getName());
                fos.close();
                logger.fine("Properties saved to : " + f.getCanonicalPath());
                System.out.println("Save prop to " + f.getCanonicalPath());
            } catch(Exception e) {
                try {
                    logger.log(Level.WARNING,  "Unable to load properties from: " + f.getCanonicalPath(), e);
                } catch(IOException ioe) {
                    logger.log(Level.WARNING, "Unable to load load properties." + ioe.getMessage(), ioe);
                }
            }
        } else {
            return null;
        }
        return props;
    }
    
}
