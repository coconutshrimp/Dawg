/**
 * 
 */
package dawg;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author jammavi
 *
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import config.ConfigurationParameters;
import dawg.TableImageRenderer;

public class RecordsPanel extends JPanel implements ConfigurationParameters {
	private static final long serialVersionUID = 1L;
	private ContestantTable table;
	ContestDescription cc;
	ContestantIcon image = new ContestantIcon("/images/coyoteSad.png");
	private ControllingFrame controller;
	private static String[] columnNames = { "Name", "ID", "Gender", "Owner", "Grooming", "Obedience", "Socialization",
			"Fetch", "Image" };

	private Class<?>[] colClasses = { String.class, Integer.class, JComboBox.class, String.class, String.class,
			String.class, String.class, String.class, Image.class };
	private Object data[][];

	public RecordsPanel() {
		controller = ControllingFrame.getInstance();
		addComponents();
	}

	
	private void addComponents() {

		// Object data[][] = cc.formatArray(cc.getAlist());
		cc = controller.getCurrentContest();
		int size = cc.getDogList().size();
		if (size == 0) {
			data = new Object[0][0];
		} else {
			ArrayList<ActualDog> dogList = cc.getDogList();
			data = formatArray(dogList);
		}
		
		// controller.getOverallState();
		ContestantTableModel model = new ContestantTableModel(data, columnNames, colClasses);

		table = new ContestantTable(model);


		TableColumn col = table.getColumnModel().getColumn(0);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		renderer.setHorizontalAlignment(JLabel.LEFT);
		
		for(int i = 0; i < 9; i++) {
			TableColumn col1 = table.getColumnModel().getColumn(i);
			col1.setCellRenderer(renderer);
		}
		
		
		
		
		col.setPreferredWidth(50);
		
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(5);
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(30);
		col = table.getColumnModel().getColumn(3);
		col.setPreferredWidth(50);
		col = table.getColumnModel().getColumn(4);
		col.setPreferredWidth(40);
		
		col = table.getColumnModel().getColumn(5);
		col.setPreferredWidth(40);
		col = table.getColumnModel().getColumn(6);
		col.setPreferredWidth(50);
		col = table.getColumnModel().getColumn(7);
		col.setPreferredWidth(15);
		col = table.getColumnModel().getColumn(8);
		col.setPreferredWidth(200);
		JScrollPane pane = new JScrollPane(table);
		
		pane.setPreferredSize(new Dimension(ConfigurationParameters.width - 400, ConfigurationParameters.height - 80));
		// table.setPreferredScrollableViewportSize(table.getPreferredSize());
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(8).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
		for (int i = 0; i < 4; i++) {

			table.getColumnModel().getColumn(i).setCellRenderer((TableCellRenderer) new DefaultTableCellRenderer() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setFont(new Font("Arial", Font.PLAIN, 14));
					return this;
				}
			});
		}
		
		for (int i = 4; i < 8; i++) {

			table.getColumnModel().getColumn(i).setCellRenderer((TableCellRenderer) new DefaultTableCellRenderer() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					setFont(new Font("Arial", Font.PLAIN, 18));
					return this;
				}
			});
		}
		table.setSize(width, height);
		add(pane);
	}

	private Object[][] formatArray(ArrayList<ActualDog> list) {

		Object[][] data = new Object[list.size()][];
		int index = 0;

		for (ActualDog dog : list) {
			Object[] dogInfo = new Object[9];
			dogInfo[0] = dog.getName();
			dogInfo[1] = dog.getId();
			dogInfo[2] = dog.getGender();
			dogInfo[3] = dog.getOwner();
			dogInfo[4] = dog.getfScore();
			dogInfo[5] = dog.getgScore();
			dogInfo[6] = dog.getoScore();
			dogInfo[7] = dog.getsScore();
			dogInfo[8] = dog.getImage();
			data[index++] = dogInfo;
		}

		return data;

	}
	public ContestantTable getTable() {
		return table;
	}

}
