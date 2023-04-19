/**
 * 
 */
package dawg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

/**
 * @author jammavi
 *
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import config.ConfigurationParameters;
import dawg.TableImageRenderer;

public class RecordsPanel extends JPanel implements ConfigurationParameters {
	private static final long serialVersionUID = 1L;
	ContestantTable table;
	ContestsClass cc;
	ContestantIcon image = new ContestantIcon("/images/coyoteSad.png");
	private ControllingFrame controller;

	public RecordsPanel() {
		controller = ControllingFrame.getInstance();
		addComponents();
	}

	private void addComponents() {

		Object data[][];
		// Object data[][] = cc.formatArray(cc.getAlist());
		cc = (ContestsClass) Persistance.restore("/db/database");
		if (cc == null || (cc != null && cc.getAlist() == null)) {
			data = new Object[0][0];
		} else {
			data = cc.formatArray(cc.getAlist());
		}
		// controller.getOverallState();
		table = new ContestantTable(data);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		// JScrollPane pane = new JScrollPane(table);
		// table.setPreferredScrollableViewportSize(table.getPreferredSize());
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 0; i < 8; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer((TableCellRenderer) new DefaultTableCellRenderer() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setFont(new Font("Arial", Font.PLAIN, 20));
					return this;
				}
			});

			table.setSize(width, height);

			JScrollPane pane = new JScrollPane(table);
			add(pane);
		}

	}

	
}
