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
	private ActualDog dog;
	private Records record;

	public ArrayList<ActualDog> getContestantList() {
		return contestantList;
	}

	private int year = 2023;

	ContestDescription contestDescr;

	ArrayList<String> alist = new ArrayList<String>();

	String[] dogNames = alist.toArray(new String[alist.size()]);

	ArrayList<ActualDog> contestantList;

	String[] categories = { "Select a category", "Grooming", "Obedience", "Socialization", "Fetch" };

	private JComboBox<String> dogNameList = new JComboBox<String>(dogNames);

	private JComboBox<String> categoryList = new JComboBox<String>(categories);

	ArrayList<ContestDescription> contestsWithDog = new ArrayList<>();

	public ContestView() {
		this.controller = ControllingFrame.getInstance();

		contestDescr = (ContestDescription) Persistance.restore("/db/database");

		contestantList = controller.getCurrentContest().getDogList();

		if (contestDescr == null) {
			contestantList = new ArrayList<ActualDog>();
		} else {
			contestantList = contestDescr.getDogList();
		}

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		alist.add("Dog Name");
		dogNames = alist.toArray(new String[alist.size()]);
		dogNameList = new JComboBox<String>(dogNames);

		dogNameList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedName = (String) dogNameList.getSelectedItem();
				// for(ContestsClass actualContestsClass
				contestantList = controller.getCurrentContest().getDogList();
				for (int i = 0; i < contestantList.size(); i++) {
					ArrayList<String> temp = new ArrayList<>();
					temp.add("Select a category");
					ActualDog y = contestantList.get(i);

					if (y.getName().equals(selectedName)) {
						System.out.println("Name:" + y.getName());
						System.out.println(selectedName);
						System.out.println("Fetch:" + y.isFetch());
						System.out.println("F score:" + y.getfScore());
						if (y.isGrooming())
							temp.add("Grooming");
						if (y.isObedience())
							temp.add("Obedience");
						if (y.isSocialization())
							temp.add("Socialization");
						if (y.isFetch())
							temp.add("Fetch");

						categories = temp.toArray(new String[temp.size()]);
						categoryList.setModel(new DefaultComboBoxModel<String>(categories));
					}
				}

				judge1Field.setText("Enter Score (0-10)");
				judge2Field.setText("Enter Score (0-10)");
				judge3Field.setText("Enter Score (0-10)");
				judge4Field.setText("Enter Score (0-10)");
				judge5Field.setText("Enter Score (0-10)");

			}
		});

		addComponents();
	}

	public void addDog(String name, String id, String owner, String gender, boolean groom, boolean obedience,
			boolean social, boolean fetch, ContestantIcon icon) {
		alist.add(name);
		ActualDog x = new ActualDog(name, id, owner, gender, groom, obedience, social, fetch, icon);

		dogNames = alist.toArray(new String[alist.size()]);
		dogNameList.setModel(new DefaultComboBoxModel<String>(dogNames));

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

			if (!typedString.equals("")) {

				if (typedString.length() > 2) {
					textField.setText("");
					textField.setText(typedString.substring(0, 2));
				}

				if (typedString.length() == 2) {
					if (typedString.charAt(1) != '0') {
						textField.setText("10");
					}
				}

				if (Integer.parseInt(typedString) > 10)
					textField.setText("10");

			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == '\n') {
				// System.out.println(e.getKeyChar());
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

			int val1 = Integer.parseInt(text1);
			int val2 = Integer.parseInt(text2);
			int val3 = Integer.parseInt(text3);
			int val4 = Integer.parseInt(text4);
			int val5 = Integer.parseInt(text5);
			if (val1 > 10 || val2 > 10 || val3 > 10 || val4 > 10 || val5 > 10) {

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

			}

		} else {

		}

	}

	public void updateDogNames() {

		ArrayList<String> names = controller.getCurrentContest().getDogNames();

		dogNames = (String[]) names.toArray(new String[0]);
		dogNameList.setModel(new DefaultComboBoxModel<String>(dogNames));

//		dogNames = alist.toArray(new String[alist.size()]);
//		dogNameList.setModel(new DefaultComboBoxModel<String>(dogNames));
	}

	protected boolean areFieldsValid() {
		return isNumber(judge1Field.getText(), judge2Field.getText(), judge3Field.getText(), judge4Field.getText(),
				judge5Field.getText());

	}

	protected void clearFields() {
		judge1Field.setText("Enter Score (0-10)");
		judge2Field.setText("Enter Score (0-10)");
		judge3Field.setText("Enter Score (0-10)");
		judge4Field.setText("Enter Score (0-10)");
		judge5Field.setText("Enter Score (0-10)");
		median.setText("");

	}

	private void addComponents() {
		// setBackground(Color.BLACK);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

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

				boolean category = true;
				int id = -1;

				if (!median.getText().equals("")) {

					String name = (String) dogNameList.getSelectedItem(), m = median.getText();

					// ContestsClass x = new ContestsClass(nameField.getText(), median.getText(),
					// (String)categoryList.getSelectedItem());
					for (int i = 0; i < contestantList.size(); i++) {
						boolean twoDogs = false;
						ActualDog dog = contestantList.get(i);
						if (name.equals(contestantList.get(i).getName())) {
							id = Integer.parseInt(dog.getId());
							if (!twoDogs)
								for (int x = i; x < contestantList.size(); x++) {
									if (name.equals(contestantList.get(i).getName()))
										twoDogs = true;

								}

						}
						if (id == Integer.parseInt(contestantList.get(i).getId())) {

							switch ((String) categoryList.getSelectedItem()) {
							case "Grooming":
								controller.getCurrentContest().grooming(dog.getId(), Integer.parseInt(m));
								// contestDescr.grooming(dog.getId(), Integer.parseInt(m));
								break;
							case "Socialization":
								controller.getCurrentContest().socialization(dog.getId(), Integer.parseInt(m));
								break;
							case "Obedience":

								controller.getCurrentContest().obedience(dog.getId(), Integer.parseInt(m));
								break;
							case "Fetch":

								controller.getCurrentContest().fetch(dog.getId(), Integer.parseInt(m));
								break;
							case "Select a category":
								category = false;
								break;

							}
						}

					}

					if (category == true && isNumber(judge1Field.getText(), judge2Field.getText(),
							judge3Field.getText(), judge4Field.getText(), judge5Field.getText(), median.getText())) {
						judge1Field.setText("Enter Score (0-10)");
						judge2Field.setText("Enter Score (0-10)");
						judge3Field.setText("Enter Score (0-10)");
						judge4Field.setText("Enter Score (0-10)");
						judge5Field.setText("Enter Score (0-10)");
						median.setText("");
						categoryList.setSelectedIndex(0);

					}
				}
			}
		});

		categoryList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				contestantList = controller.getCurrentContest().getDogList();
				String category = (String) categoryList.getSelectedItem();
				String doggo = (String) dogNameList.getSelectedItem();
				for (int i = 0; i < contestantList.size(); i++) {

					ActualDog dog = contestantList.get(i);
					if (dog.getName().equals(doggo)) {
						if (category.equals("Grooming") && (dog.getgScore() > -1 && dog.getgScore() <= 10)) {
							System.out.println(dog.getName() + " " + dog.getgScore());
							finalizeButton.setEnabled(false);
						}

						else if (category.equals("Obedience") && dog.getoScore() != 0) {
							finalizeButton.setEnabled(false);
						}

						else if (category.equals("Socialization") && dog.getsScore() != 0) {
							finalizeButton.setEnabled(false);
						}

						else if (category.equals("Fetch") && dog.getfScore() != 0) {
							finalizeButton.setEnabled(false);

						}

						else
							finalizeButton.setEnabled(true);
						
						if(category.equals("Grooming") && dog.getgScore()==0)
							finalizeButton.setEnabled(true);
						if(category.equals("Obedience") && dog.getoScore()==0)
							finalizeButton.setEnabled(true);

						if(category.equals("Socialization") && dog.getsScore()==0)
							finalizeButton.setEnabled(true);

						if(category.equals("Fetch") && dog.getfScore()==0)
							finalizeButton.setEnabled(true);


					}
				}
			}

		});

		judge1Field = new JTextField("Enter Score (0-10)");
		judge1Field.addKeyListener(new ContestViewKeyListener(judge1Field));
		judge1Field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();
			}
		});
		judge2Field = new JTextField("Enter Score (0-10)", 2);
		judge2Field.addKeyListener(new ContestViewKeyListener(judge2Field));
		judge2Field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();
			}
		});
		judge3Field = new JTextField("Enter Score (0-10)", 2);
		judge3Field.addKeyListener(new ContestViewKeyListener(judge3Field));
		judge3Field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();
			}
		});
		judge4Field = new JTextField("Enter Score (0-10)", 2);
		judge4Field.addKeyListener(new ContestViewKeyListener(judge4Field));
		judge4Field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();
			}
		});
		judge5Field = new JTextField("Enter Score (0-10)", 2);
		judge5Field.addKeyListener(new ContestViewKeyListener(judge5Field));
		judge5Field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (areFieldsValid())
					calculateMedian();
			}
		});
		median = new JTextField("", 2);
		median.setEditable(false);

		judge1Field.addFocusListener(new ContestViewFocusListener(judge1Field));
		judge2Field.addFocusListener(new ContestViewFocusListener(judge2Field));
		judge3Field.addFocusListener(new ContestViewFocusListener(judge3Field));
		judge4Field.addFocusListener(new ContestViewFocusListener(judge4Field));
		judge5Field.addFocusListener(new ContestViewFocusListener(judge5Field));

		dogNameList.setPreferredSize(new Dimension(10, 10));

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(dLabel)
						.addComponent(categorylabel).addComponent(label1).addComponent(label2).addComponent(label3)
						.addComponent(label4).addComponent(label5).addComponent(label6))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(dogNameList)
						.addComponent(categoryList).addComponent(judge1Field).addComponent(judge2Field)
						.addComponent(judge3Field).addComponent(judge4Field).addComponent(judge5Field)
						.addComponent(median).addComponent(finalizeButton)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(dLabel)
						.addComponent(dogNameList))
				.addGap(25)

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