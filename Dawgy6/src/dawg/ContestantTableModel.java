package dawg;

import javax.swing.table.DefaultTableModel;


public class ContestantTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
//	private static String[] columnNames = { "Name", "ID", "Gender", "Owner's Name", "Grooming", "Obedience",
//	"Socialization", "Fetch", "Image" };
	static Object[][] data = { {"Name", "ID", "Gender", "Owner's Name", "Grooming", "Obedience",
		"Socialization", "Fetch", new ContestantIcon("/images/coyoteSad.png")} };
	private Class<?>[] colClasses;

		
	public ContestantTableModel( Object[][] data1, String[] columnNames, Class<?>[] colClasses) {
		super(data,columnNames);
		this.colClasses = colClasses;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		// TODO Auto-generated method stub
		return colClasses[column];
	}
	
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
	return false;
	}

}