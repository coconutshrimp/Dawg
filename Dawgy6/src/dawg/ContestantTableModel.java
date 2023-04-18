package dawg;

import javax.swing.table.DefaultTableModel;


public class ContestantTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	static String column[] = { "Name", "Id", "Gender", "Owner's Name", "Grooming", "Obedience", "Socialization", "Fetch",
	"Image" };
	static Object data[][] = { { "Eddie", "1", "Male", "Jake", "N/A", "8", "9", "N/A", null } };
	
	public ContestantTableModel( Object[][] data, String[] columnNames) {
		super(ContestantTableModel.data, ContestantTableModel.column);
	}

	
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
	return false;
	}

}
