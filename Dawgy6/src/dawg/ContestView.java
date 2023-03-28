/**
 * 
 */
/**
 * 
 */
package dawg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author jammavi
 *
 */
public class ContestView extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControllingFrame controller;
	private final int WIDTH = 250, HEIGHT = 500;

	private JTextField judge1Field = new JTextField("0", 2);
	private JTextField judge2Field, judge3Field, judge4Field, judge5Field, median, nameField;
	private int med;

	String[] dogNames = { "Add Dog Name", "test1", "test2", "test3" };
	private JComboBox<String> dogList = new JComboBox<String>(dogNames);

	public ContestView() {
		this.controller = ControllingFrame.getInstance();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		addComponents();
	}

	private class ContestViewFocusListener implements FocusListener {

		private JTextField textField;

		public ContestViewFocusListener(JTextField textField) {
			this.textField = textField;
		}

		@Override
		public void focusGained(FocusEvent event) {
			String str = textField.getText();
			if (str.equalsIgnoreCase("Enter Score (0-10)")) {
				textField.setText("");
				System.out.println("Test");
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (textField.getText().equals("")) {
				textField.setText("Enter Score (0-10)");
			}

		}
	}

	private class ContestViewKeyListener implements KeyListener {

		private JTextField textField;

		public ContestViewKeyListener(JTextField textField) {
			this.textField = textField;
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

			String typedString = textField.getText();

			if (typedString.length() > 2) {
				textField.setText("");
				textField.setText(typedString.substring(0, 2));
			}
			System.out.println(textField.getText());

			if (typedString.length() == 2) {
				if (typedString.charAt(1) != '0') {
					textField.setText("10");
				}
			}

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == '\n') {
				System.out.println(e.getKeyChar());
				calculateMedian();
			}

		}

	}

	private boolean isNumber(String... strings) {
		String str = "";
		for (String s : strings)
			str += s;
		char[] digits = str.toCharArray();
		for (char ch : digits) {
			if (!Character.isDigit(ch))
				return false;
		}
		return true;
	}

	public void calculateMedian() {
		String text1 = judge1Field.getText();
		String text2 = judge2Field.getText();
		String text3 = judge3Field.getText();
		String text4 = judge4Field.getText();
		String text5 = judge5Field.getText();

		// check is any of the test are not digits
		if (isNumber(text1, text2, text3, text4, text5)) {
			System.out.println("Numbers");
			int val1 = Integer.parseInt(text1);
			int val2 = Integer.parseInt(text2);
			int val3 = Integer.parseInt(text3);
			int val4 = Integer.parseInt(text4);
			int val5 = Integer.parseInt(text5);
			if (val1 > 10 || val2 > 10 || val3 > 10 || val4 > 10 || val5 > 10) {
				System.out.println("Too high");
			}

			else {
				ArrayList<Integer> array = new ArrayList<>();
				array.add(val1);
				array.add(val2);
				array.add(val3);
				array.add(val4);
				array.add(val5);
				Collections.sort(array);
				int middleIndex = array.size() / 2;
				med = array.get(middleIndex);
				median.setText(String.valueOf(med));
				System.out.println(med);
			}

		} else {
			System.out.println("Nums only");
		}

	}

	private void addComponents() {
		// setBackground(Color.BLACK);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel dLabel = new JLabel("Dog Name: ", JLabel.CENTER);
		dLabel.setLabelFor(nameField);

		JLabel label1 = new JLabel("Judge 1: ", JLabel.CENTER);
		label1.setLabelFor(judge1Field);

		JLabel label2 = new JLabel("Judge 2: ", JLabel.CENTER);
		label2.setLabelFor(judge2Field);

		JLabel label3 = new JLabel("Judge 3: ", JLabel.CENTER);
		label3.setLabelFor(judge3Field);

		JLabel label4 = new JLabel("Judge 4: ", JLabel.CENTER);
		label4.setLabelFor(judge4Field);

		JLabel label5 = new JLabel("Judge 5: ", JLabel.CENTER);
		label5.setLabelFor(judge5Field);

		JLabel label6 = new JLabel("Median: ", JLabel.CENTER);
		label6.setLabelFor(median);

		JButton finalizeButton = new JButton("Finalize Score");
		finalizeButton.addActionListener(controller);
		finalizeButton.setActionCommand("Finalize");

		judge1Field = new JTextField("Enter Score (0-10)");
		judge1Field.addKeyListener(new ContestViewKeyListener(judge1Field));

		judge2Field = new JTextField("Enter Score (0-10)", 2);
		judge2Field.addKeyListener(new ContestViewKeyListener(judge2Field));

		judge3Field = new JTextField("Enter Score (0-10)", 2);
		judge3Field.addKeyListener(new ContestViewKeyListener(judge3Field));

		judge4Field = new JTextField("Enter Score (0-10)", 2);
		judge4Field.addKeyListener(new ContestViewKeyListener(judge4Field));

		judge5Field = new JTextField("Enter Score (0-10)", 2);
		judge5Field.addKeyListener(new ContestViewKeyListener(judge5Field));

		median = new JTextField("Press Enter to calculate", 2);
		median.setEditable(false);

		judge1Field.addFocusListener(new ContestViewFocusListener(judge1Field));
		judge2Field.addFocusListener(new ContestViewFocusListener(judge2Field));
		judge3Field.addFocusListener(new ContestViewFocusListener(judge3Field));
		judge4Field.addFocusListener(new ContestViewFocusListener(judge4Field));
		judge5Field.addFocusListener(new ContestViewFocusListener(judge5Field));

		dogList.setPreferredSize(new Dimension(10, 10));

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(dLabel)
						.addComponent(label1).addComponent(label2).addComponent(label3).addComponent(label4)
						.addComponent(label5).addComponent(label6))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(dogList)
						.addComponent(judge1Field).addComponent(judge2Field).addComponent(judge3Field)
						.addComponent(judge4Field).addComponent(judge5Field).addComponent(median)
						.addComponent(finalizeButton)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(dLabel).addComponent(dogList))
				.addGap(25)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1)
						.addComponent(judge1Field))
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2)
						.addComponent(judge2Field))
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label3)
						.addComponent(judge3Field))
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label4)
						.addComponent(judge4Field))
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label5)
						.addComponent(judge5Field))
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label6)
						.addComponent(median))
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(finalizeButton))

		);

	}
}
