package dawg;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ContestantTable extends JTable {

	private static final long serialVersionUID = 1L;
	private final int ROWHEIGHT = 100;
	private StringBuilder output;
//	private static String[] columnNames = { "Name", "ID", "Gender", "Owner's Name", "Grooming", "Obedience",
//			"Socialization", "Fetch", "Image" };
//
//	private Class[] colClasses = { String.class, Integer.class, JComboBox.class, String.class, String.class,
//			String.class, String.class, String.class, ImageIcon.class };

	public ContestantTable(ContestantTableModel model) {
		super(model);
		getSelectionModel().addListSelectionListener(new RowListener());
		setRowHeight(ROWHEIGHT);
		setPreferredScrollableViewportSize(new Dimension(900, 300));
		setFillsViewportHeight(true);
		setDefaultRenderer(ImageIcon.class, new TableImageRenderer(getDefaultRenderer(ImageIcon.class)));
		//setDefaultRenderer(Image.class, new TableImageRenderer(getDefaultRenderer(Image.class)));
	}

	private void outputSelection() {
		output.append(String.format("Lead: %d, %d. ", getSelectionModel().getLeadSelectionIndex(),
				getColumnModel().getSelectionModel().getLeadSelectionIndex()));
		output.append("Rows:");
		for (int c : getSelectedRows()) {
			output.append(String.format(" %d", c));
		}
		output.append(". Columns:");
		for (int c : getSelectedColumns()) {
			output.append(String.format(" %d", c));
		}
		output.append(" Name: " + getValueAt(getSelectedRow(), 0) + ".\n");
	}

	private class RowListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			output = new StringBuilder();
			output.append("ROW SELECTION EVENT. ");
			//outputSelection();
			//System.out.println(output);
		}
	}
}