package com.vexterra;

public class Model extends java.util.Observable {
    private String[][] allRows;
    private int[] columnWidths;
    private String[] columnNames;
    
    public Model() {
        super();
        // name, location, size, smoking, rate, date, owner
        this.columnNames = new String[]{"Name", "Location", "Size", "Smoking", "Rate", "Date", "Owner","RecId"};
        this.columnWidths = new int[]{ 10, 10, 5, 5, 10, 10, 10, 10 };

        Object[][] data = {
            {"Anh", "Virginia", "Medium", "Y", "$100", "01/01/2017", null,null},
        };
        
        setChanged();
        System.out.println("Model constructed");
    }
     
    public String[] getColumnNames()  {
        return columnNames;
    }
    
    public int[] getColumnWidths() {
        return columnWidths;
    }
    
    public String[][] getAllRows() {
        return allRows;
    }
    
    /**
     * Sets the rows to be displayed. Used by the controller.
     * @param newRows data for newRows
     */
    public void setDisplayRows(String[][] newRows)  {
        allRows = newRows;
        setChanged();
    }

}
