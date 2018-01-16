 package com.kyron.rmi;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * This panel contains a JTable. It looks at the model and updates the rows that are displayed in the JTable.
 *
 */
public class TablePanel extends BasePanel {

    JTable cTable = null;
    GenTableModel localTableModel = null;
    Model mainModel = null;  // plug-in the data model (Observable)

    /**
     * This inner class implements the TableModel used by JTable.
     */
    private class GenTableModel extends AbstractTableModel    {

        String[] colNames = new String[0];
        String[][] displayRows = new String[0][0];

        // constructor
        public GenTableModel() {
            if (mainModel != null) {
                displayRows = mainModel.getAllRows();  // all rows
            } else {
                displayRows = new String[0][0];
            }
        }

        // refresh model
        public void refresh() {
            if (mainModel != null) {
                displayRows = mainModel.getAllRows();
                colNames = mainModel.getColumnNames();
            }
        }

        public int getColumnCount() {
            return colNames.length;
        }

        public int getRowCount() {
            if (displayRows != null) {
                return displayRows.length;
            } else {
                return 0;
            }
        }

        public void setColumnWidths() {
            TableColumn column = null;
            if (mainModel != null) {
                int[] widths = mainModel.getColumnWidths();
                if (widths != null) {
                    for (int i = 0; i < widths.length; i++) {
                        column = cTable.getColumnModel().getColumn(i);
                        int i1 = colNames[i].length();
                        int i2 = widths[i];

                        column.setPreferredWidth((i1 > i2 ? i1 : i2) * 10);
                    }
                }
            }
        }

        public String getColumnName(int col) {
            return colNames[col];
        }

        public Object getValueAt(int row, int col) {
            if (displayRows != null) {
                return displayRows[row][col];
            } else {
                return null;
            }
        }
    }

    public TablePanel() {
        super();
        localTableModel = new GenTableModel();  // default model
        cTable = new JTable(localTableModel);
        this.setLayout(new BorderLayout());
        cTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        cTable.setCellSelectionEnabled(false);
        cTable.setRowSelectionAllowed(true);
        //cTable.setFillsViewportHeight(true);
        JScrollPane jsp = new JScrollPane(cTable);
        add(BorderLayout.CENTER, jsp);
    }

    public void actionPerformed(ActionEvent ae) {
        postUserActionEvent(ae);
    }
    // Create new ActionEvent "BOOK_ROOM,<rownum>"
    private void doDoubleClick(MouseEvent me) {
        int rowx = cTable.getSelectedRow();
      	System.out.println("TablePanel.doDoubleClick, rowx = " +rowx);
        if (mainModel != null) {
            ActionEvent ae = new ActionEvent(this, me.getID(), "BOOK_ROOM," + rowx);
            postUserActionEvent(ae);
        }
    }

    // Not use..... 
    public void clearTable() {
        DefaultTableModel dm = (DefaultTableModel) cTable.getModel();
        dm.getDataVector().removeAllElements();
    }

    public static void main(String[] args) {
        Model cm = new Model();
        JFrame jf = new JFrame();
        TablePanel cdp = new TablePanel();
        cdp.update(cm, new Boolean(true));
        jf.setSize(600, 400);
        jf.getContentPane().add("Center", cdp);
        jf.pack();
        jf.setVisible(true);
    }

    /**
     * Updates the view.
     * @param model The ClientModel object that contains the rows to be displayed.
     * @param obj This parameter should be of class Boolean and it indicates whether the whole table structure has to be changed (for change in table columns) or only new rows have to be displayed.
     *
     */
    public void update(Observable model, Object obj) {

        System.out.println("TablePanel.update()");
        this.mainModel = (Model) model;
        if (obj instanceof Boolean) {
            updateTableView(((Boolean) obj).booleanValue());
        } else {
            updateTableView(false);
        }
    }

    /**
     * Updates the Jtable with new rows.
     * @param boolean totalUpdate if true, it means the columns have changed and the table structure has to change. If false, only the rows are refreshed.
     */
    private void updateTableView(boolean totalUpdate) {
        localTableModel.refresh();
        if (totalUpdate) {
            localTableModel.fireTableStructureChanged();
            localTableModel.setColumnWidths();
            this.revalidate();
            cTable.revalidate();
            cTable.repaint();
        } else {
            localTableModel.fireTableDataChanged();
        }
    }

    public int getSelectedIndex() {
        return cTable.getSelectedRow();
    }
}

