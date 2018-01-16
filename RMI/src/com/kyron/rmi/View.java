// Similar to ClientFrame
 package com.kyron.rmi;
 
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * ViewFrame includes:
 * - actionPanel - ActionPanel - BasePanel
 * - dataPanel - to do
 * Do layout the panels
 * Provide a hook to set ActionListener to actionPanel
 * Provide a hook to let an outside model to Observe actionPanel 
 */
public class View extends JFrame {

    private TablePanel tablePanel;
    private ActionPanel actionPanel;
    private ActionListener al;

    public View() {
        super("MyView");
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        actionPanel = new ActionPanel();
        this.getContentPane().add(BorderLayout.NORTH, actionPanel);
        tablePanel = new TablePanel();
        this.getContentPane().add(BorderLayout.CENTER, tablePanel);
    }

    public void setController(ActionListener pal) {
        this.al = pal;
        actionPanel.addUserActionListener(al);
        tablePanel.addUserActionListener(al);
        System.out.println("View setController");
    }

    /**
     * This method is called by the Controller to set the model for this frame(view).
     */
    public void setModel(Model m) {
        m.addObserver(actionPanel);
        m.addObserver(tablePanel);
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }
    /*public static void main (String[] args) {
    System.out.println("hello");
    View v = new View();
    Controller c = new Controller(v);  // create controller for this view
    v.setSize(650,300);
    v.setLocationRelativeTo(null); // makes sure that the frame is centered on the screen.
    v.setVisible(true);
    }*/
}  // end class 
