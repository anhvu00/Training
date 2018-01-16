package com.vexterra;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.vexterra.ActionPanel;
import com.vexterra.Model;
import com.vexterra.TablePanel;

public class View extends JFrame {

	private static final long serialVersionUID = -8623401896113377791L;
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
	 * This method is called by the Controller to set the model for this
	 * frame(view).
	 */
	public void setModel(Model m) {
		m.addObserver(actionPanel);
		m.addObserver(tablePanel);
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}
}
