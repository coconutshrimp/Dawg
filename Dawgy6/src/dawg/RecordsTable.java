/**
 * 
 */
package dawg;

import java.awt.Color;

/**
 * @author jammavi
 *
 */
import javax.swing.*;
import javax.swing.table.JTableHeader;

import config.ConfigurationParameters;

public class RecordsTable extends JPanel implements ConfigurationParameters {
	private static final long serialVersionUID = 1L;
	JTable table;

	public RecordsTable() {

		addComponents();

	}

	private void addComponents() {

		String data[][] = { { "Eddie", "1", "Male", "Jake", "N/A", "8", "9", "N/A" } };
		String column[] = { "Name", "Id", "Gender", "Owner's Name", "Grooming", "Obedience", "Socialization", "Fetch" };

		table = new JTable(data, column);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		//JScrollPane pane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table.setSize(width, height);
		JScrollPane pane = new JScrollPane(table);
		add(pane);

	}

	public static void main(String[] args) {

	}
}
