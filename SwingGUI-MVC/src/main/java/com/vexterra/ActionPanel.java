// Similar to ControlPanel
 package com.vexterra;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionPanel extends BasePanel  implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5386483255479362272L;
	JTextField nameTF = new JTextField(20);
    JTextField locationTF = new JTextField(20);
    JButton searchBtn = new JButton("Search");
    JButton bookBtn = new JButton("Reserve");
    JButton viewAllBtn = new JButton("ViewAll");
    JButton clearBtn = new JButton("Clear");
	
	public ActionPanel() {
		super();
        searchBtn.setToolTipText("Search records based on name and/or location.");
        searchBtn.setActionCommand("SEARCH_NAME_LOCATION");		
        searchBtn.addActionListener(this);
        
        bookBtn.setToolTipText("Book selected room");
        bookBtn.setActionCommand("BOOK_ROOM");
        bookBtn.addActionListener(this);
        
        viewAllBtn.setToolTipText("Display all rooms");
        viewAllBtn.setActionCommand("VIEW_ALL");
        viewAllBtn.addActionListener(this); 
        	
        clearBtn.setToolTipText("Clear display");
        clearBtn.setActionCommand("CLEAR");
        clearBtn.addActionListener(this);

        JPanel tempPanel1 = new JPanel();
        tempPanel1.setLayout(new FlowLayout());
        tempPanel1.add(new JLabel("Name: "));
        tempPanel1.add(nameTF);
        
        JPanel tempPanel2 = new JPanel();
        tempPanel2.setLayout(new FlowLayout());
        tempPanel2.add(new JLabel("Location: "));
        tempPanel2.add(locationTF);
        
        JPanel tempPanel3 = new JPanel();
        tempPanel3.setLayout(new FlowLayout());
        tempPanel3.add(searchBtn);
        tempPanel3.add(bookBtn);                	               	
        tempPanel3.add(viewAllBtn);
        tempPanel3.add(clearBtn); 
        	
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	
		this.add(tempPanel1);        	
		this.add(tempPanel2);        	
		this.add(tempPanel3);        	
	}

	public void update(Observable observable, Object obj) {
		//System.out.println("ActionPanel update()");
	}
	
   public void actionPerformed(ActionEvent ae) {
        //System.out.println("ActionPanel action="+ae.getActionCommand());
        if(ae.getSource() == searchBtn) {
            String name = nameTF.getText();
            String location = locationTF.getText();
            ae = new ActionEvent(searchBtn, ae.getID(), "SEARCH_NAME_LOCATION,"+name+","+location);
        } else if(ae.getSource() == clearBtn) {
            nameTF.setText("");
            locationTF.setText("");
        }
        postUserActionEvent(ae);
   }
}
