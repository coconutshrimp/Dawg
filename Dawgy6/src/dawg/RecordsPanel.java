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
import dawg.TableImageRenderer;

public class RecordsPanel extends JPanel implements ConfigurationParameters {
	private static final long serialVersionUID = 1L;
	JTable table;
	ContestantIcon image = new ContestantIcon("/images/coyoteSad.png");

	public RecordsPanel() {
		addComponents();
	}

	private void addComponents() {
		String column[] = { "Name", "Id", "Gender", "Owner's Name", "Grooming", "Obedience", "Socialization", "Fetch",
				"Image" };
		Object data[][] = { { "Eddie", "1", "Male", "Jake", "N/A", "8", "9", "N/A", image } };
 
		table = new ContestantTable(column, data);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		// JScrollPane pane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// table.setSize(width, height);
		JScrollPane pane = new JScrollPane(table);
		add(pane);

	}

	public static void main(String[] args) {

	}
}
