package dawg;

import javax.swing.table.AbstractTableModel;

public class ContestantTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = null;
	private Object[][] data =null;

	
	public ContestantTableModel(String[] headers, Object[][] data){
		columnNames = headers;
		this.data = data;
	}
	
    public int getColumnCount() {
        return columnNames.length;
    }
 
    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
 
}
