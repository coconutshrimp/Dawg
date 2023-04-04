/**
 * 
 */
/**
 * 
 */
package dawg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
	private Records record;
	
	ArrayList<ContestsClass> list = new ArrayList<ContestsClass>();
	
	ContestsClass x;
	
	private ContestsClass x1, x2, x3;
	
	ArrayList<String> clist = new ArrayList<String>();
	
	String [] contestNames = clist.toArray(new String[clist.size()]);

	ArrayList<String> alist = new ArrayList<String>();
	
	String[] dogNames = alist.toArray(new String[alist.size()]);
	
	ArrayList<ContestsClass> actualContest = new ArrayList<ContestsClass>();
	
	String [] categories = {"Select a category", "Grooming", "Obedience", "Socialization", "Fetch"};
	
	
	private JComboBox<String> dogList = new JComboBox<String>(dogNames);
	private JComboBox<String> contestList = new JComboBox<String>(contestNames);
	private JComboBox<String> categoryList = new JComboBox<String>(categories);
	
	ArrayList<ContestsClass> contestsWithDog= new ArrayList<>();

	public ContestView() {
		this.controller = ControllingFrame.getInstance();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		alist.add("Dog Name");
		dogNames = alist.toArray(new String[alist.size()]);
		dogList = new JComboBox<String>(dogNames);
		
		
		contestNames = clist.toArray(new String[clist.size()]);
		contestList = new JComboBox<String>(contestNames);
		
		dogList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) dogList.getSelectedItem();
				for(int i = 0; i < actualContest.size(); i++) {
					if(actualContest.get(i).getName().equals("Current Contest")) {
						ArrayList<String> temp = new ArrayList<>();
						temp.add("Select a category");
						ActualDog y = actualContest.get(i).findDog(selected);
						if(y.isGrooming())
							temp.add("Grooming");
						if(y.isObedience())
							temp.add("Obedience");
						if(y.isSocialization())
							temp.add("Socialization");
						if(y.isFetch())
							temp.add("Fetch");
						
						categories = temp.toArray(new String[temp.size()]);
						categoryList.setModel(new DefaultComboBoxModel<String>(categories));
					}
				}
			}
		});
		
		actualContest.add(new ContestsClass("Current Contest"));
		
		addComponents();
	}
	public void setArray(ArrayList<String> x) {
		clist = x;
		contestNames = clist.toArray(new String[clist.size()]);
		contestList.setModel(new DefaultComboBoxModel<String>(contestNames));
	}
	
	


	public void addDog(String name, String id, String owner, String gender, boolean groom,
			boolean obedience, boolean social, boolean fetch) {
		alist.add(name);
		
		dogNames = alist.toArray(new String[alist.size()]);
		dogList.setModel(new DefaultComboBoxModel<String>(dogNames));
		for(int i = 0; i < actualContest.size(); i++) {
			if(actualContest.get(i).getName().equals("Current Contest")) {
				actualContest.get(i).addDog(name, id, owner, gender, groom, obedience, social, fetch);
			}
				
		}
			
		
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
				
			}
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (textField.getText().equals("")) {
				textField.setText("Enter Score (0-10)");
			}
			calculateMedian();

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
	
	public void addContest(String name) {
		actualContest.add(new ContestsClass(name));
	}

	private void addComponents() {
		// setBackground(Color.BLACK);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel contestlabel = new JLabel("Contest: ", JLabel.CENTER);
		contestlabel.setLabelFor(contestlabel);
		
		JLabel categorylabel = new JLabel("Category: ", JLabel.CENTER);
		categorylabel.setLabelFor(categorylabel);
		
		
		
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
		finalizeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) dogList.getSelectedItem(), m = median.getText(), c = (String)contestList.getSelectedItem();
				System.out.println(actualContest.get(0).getDogCount());
				//ContestsClass x = new ContestsClass(nameField.getText(), median.getText(), (String)categoryList.getSelectedItem());
				for(int i = 0; i < actualContest.size(); i++) {
					
					switch((String)categoryList.getSelectedItem()) {
					case "Grooming":
						actualContest.get(i).Grooming(c, name, Integer.parseInt(m));
					}
				}
				
				
			}
		});
		
		

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
		contestList.setPreferredSize(new Dimension(10, 10));

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(dLabel).addComponent(contestlabel).addComponent(categorylabel)
						.addComponent(label1).addComponent(label2).addComponent(label3).addComponent(label4)
						.addComponent(label5).addComponent(label6))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(dogList).addComponent(contestList)
						.addComponent(categoryList).addComponent(judge1Field).addComponent(judge2Field).addComponent(judge3Field)
						.addComponent(judge4Field).addComponent(judge5Field).addComponent(median)
						.addComponent(finalizeButton)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(dLabel).addComponent(dogList))
				.addGap(25)
				
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(contestlabel)
						.addComponent(contestList))
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(categorylabel)
						.addComponent(categoryList))
				.addGap(20)
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