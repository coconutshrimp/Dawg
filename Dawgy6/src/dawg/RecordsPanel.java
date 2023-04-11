/**
 * 
 */
package dawg;

import java.awt.Color;
import java.util.ArrayList;

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
	Object data[][];
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
	
	
	public void updateTable(ArrayList<ActualDog> list) {
		data = new Object[list.size()][9];
		for(int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).getName();
			data[i][1] = list.get(i).getId();
			data[i][2] = list.get(i).getGender();
			data[i][3] = list.get(i).getOwner();
			data[i][4] = list.get(i).getgScore();
			data[i][5] = list.get(i).getoScore();
			data[i][6] = list.get(i).getsScore();
			data[i][7] = list.get(i).getfScore();
			
		}
		String column[] = { "Name", "Id", "Gender", "Owner's Name", "Grooming", "Obedience", "Socialization", "Fetch",
		"Image" };
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
	

	
}
