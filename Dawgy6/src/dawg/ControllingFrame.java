/**
 * 
 */
package dawg;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import auth.Authenticator;
import config.ConfigurationParameters;

//import image_dnd.ImagePanel;
/**
 * @author jammavi
 *
 */
public class ControllingFrame extends JFrame implements ActionListener, FocusListener, ConfigurationParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;

	// Container panel has two components, 1. The info panel, and 2. the
	// corresponding button panel.
	private JPanel containerPanel;
	private static JPanel informationPanel;
	private static String info;
	private JPanel buttonPanel;
	private String loginError = "<html>Wrong <u>Username</u> and <u>Password</u> combination!<br>&nbsp;&nbsp;&nbsp;&nbsp; Try again!</html>",
			pictureError = "Upload image!";

	private String categoryError = "<html>Please select at least one category!</html>";

	private String regLogin, regPasswd;

	private StartUpScreen authScreen;
	private ButtonPanelForAuthentication authButtons;
	private FourButtons fourButtons;

	private ContestView contest;
	private RegisterContainer register;
	private Authenticator auth;
	private Records records;
	private boolean finalizeRegister;
	private RecordsPanel recordsPanel;
	private ContestDescription currentContest;

	private static ControllingFrame instance = null;

	private ControllingFrame(String title) {
		// initial setup
		auth = new Authenticator();
		currentContest = (ContestDescription) Persistance.restore("/db/database");
		if (currentContest == null) {
			currentContest = new ContestDescription("2023");
		}
		instance = this;
		setBackground(Color.BLACK);
		setTitle(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (currentContest != null)
					try {
						Persistance.commit(currentContest, "/db/database");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				dispose();
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// setLocationRelativeTo(null); //sets the pop up screen to the bottom right
		// corner
		Dimension screenDimension = getToolkit().getScreenSize();
		setLocation(screenDimension.width / 2 - WIDTH / 2, screenDimension.height / 2 - HEIGHT / 2);
		addComponents();
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			System.out.println("Errors setting UI look and feel!");
			System.exit(0);
		}
		pack();
		setVisible(true);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public JPanel getContainerPanel() {
		return containerPanel;
	}

	public static JPanel getInformationPanel() {
		return informationPanel;
	}

	public static String getInfo() {
		return info;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public String getLoginError() {
		return loginError;
	}

	public String getPictureError() {
		return pictureError;
	}

	public String getCategoryError() {
		return categoryError;
	}

	public String getRegLogin() {
		return regLogin;
	}

	public String getRegPasswd() {
		return regPasswd;
	}

	public StartUpScreen getAuthScreen() {
		return authScreen;
	}

	public ButtonPanelForAuthentication getAuthButtons() {
		return authButtons;
	}

	public FourButtons getFourButtons() {
		return fourButtons;
	}

	public ContestView getContest() {
		return contest;
	}

	public RegisterContainer getRegister() {
		return register;
	}

	public Authenticator getAuth() {
		return auth;
	}

	public Records getRecords() {
		return records;
	}

	public boolean isFinalizeRegister() {
		return finalizeRegister;
	}

	public RecordsPanel getRecordsPanel() {
		return recordsPanel;
	}

	public ContestDescription getCurrentContest() {
		return currentContest;
	}

	public static ControllingFrame getInstance() {
		return getInstance("Demo");

	}

	public static void setSelectedContest(String contest) {
		CardLayout layout = (CardLayout) informationPanel.getLayout();
		layout.show(informationPanel, "Table");
		info = contest;

	}

	public static ControllingFrame getInstance(String title) {
		if (instance == null) {
			new ControllingFrame(title);
		} else {
			if (!instance.getTitle().equals(title))
				instance.setTitle(title);
		}
		return instance;
	}

	private void addComponents() {
		setLayout(new BorderLayout());

		containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());

		informationPanel = new JPanel();
		informationPanel.setLayout(new CardLayout(10, 10));

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new CardLayout(10, 10));

		// add the various information panels to be switched to the info container
		// add the corresponding buttons to the buttons panel

		authScreen = new StartUpScreen(this, "images/dog_logo_altered.png");
		contest = new ContestView();
		register = new RegisterContainer(this);
		records = new Records();
		recordsPanel = new RecordsPanel();

		informationPanel.add(authScreen, "TITLE");
		informationPanel.add(contest, "Contest View");
		informationPanel.add(register, "Register");
		informationPanel.add(records, "Records");
		informationPanel.add(recordsPanel, "Table");

		containerPanel.add(informationPanel, BorderLayout.CENTER);

		authButtons = new ButtonPanelForAuthentication();
		fourButtons = new FourButtons();
		buttonPanel.add(authButtons, "AUTH");
		buttonPanel.add(fourButtons, "4Buttons");
		containerPanel.add(buttonPanel, BorderLayout.LINE_END);

		add(containerPanel, BorderLayout.CENTER);

	}

	public void updateTable(ArrayList<ActualDog> alist) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		Object o = null;
		o = e.getSource();
		switch (actionCommand) {

		case "Save":
			finalizeRegister = true;

			if (register.getReg().getNameField().getText().equalsIgnoreCase("Enter name")) {
				register.getReg().setNameRed();
				finalizeRegister = false;

			}
			if (register.getReg().getIdField().getText().equalsIgnoreCase("Enter ID")) {
				register.getReg().setIDRed();
				finalizeRegister = false;
			}
			if (register.getReg().getGenderDropDown().equalsIgnoreCase("Gender")) {
				register.getReg().setGenderRed();
				finalizeRegister = false;
			}
			if (register.getReg().getOwnerField().getText().equalsIgnoreCase("Enter Owner's Name")) {
				register.getReg().setOwnerRed();
				finalizeRegister = false;
			}

			boolean x1, x2, x3, x4;

			x1 = register.getCbp().getFetch();
			x2 = register.getCbp().getGrooming();
			x3 = register.getCbp().getSocialization();
			x4 = register.getCbp().getObedience();
			if (x1 == false && x2 == false && x3 == false && x4 == false) {
				JOptionPane.showMessageDialog(this, categoryError, "Error", JOptionPane.ERROR_MESSAGE);
				finalizeRegister = false;
			}

			DnDImagePanel imgPanel = register.getDnDImagePanel();

			System.out.println(imgPanel.getImage().hashCode());
			System.out.println("Image changed " +imgPanel.imageChanged());
			if (!imgPanel.imageChanged()) {
				JOptionPane.showMessageDialog(this, pictureError, "Picture", JOptionPane.ERROR_MESSAGE);
				finalizeRegister = false;
			}

			if (finalizeRegister) {

//				//contestclass.addDog(register.getReg().getNameField().getText(),
//						register.getReg().getIdField().getText(), register.getReg().getOwnerField().getText(),
//						register.getReg().getGenderDropDown(), register.getCbp().getGrooming(),
//						register.getCbp().getObedience(), register.getCbp().getSocialization(),
//						register.getCbp().getFetch(), register.getDnDImagePanel());
//				Object[] data = new Object[9];
//
//				data[0] = register.getReg().getNameField().getText();
//				data[1] = register.getReg().getIdField().getText();
//				data[3] = register.getReg().getOwnerField().getText();
//				data[2] = register.getReg().getGenderDropDown();
//				data[4] = register.getCbp().getGrooming();
//				data[5] = register.getCbp().getObedience();
//				data[6] = register.getCbp().getSocialization();
//				data[7] = register.getCbp().getFetch();
//				Image img = register.getDnDImagePanel().getImage();
//				ImageIcon icon = new ImageIcon(img);
//				data[8] = icon;

//				for (int i = 0; i < data.length; i++) {
//					data[i] = "hello";
//				} 

				// ControllingFrame collect all the data into an array
				// DefaultTableModel model = (DefaultTableModel)
				// recordsPanel.getTable().getModel();
				// model.addRow(dog);
				// model.addRow(data);

				recordsPanel.repaint();
				contest.addDog(register.getReg().getNameField().getText(), register.getReg().getIdField().getText(),
						register.getReg().getOwnerField().getText(), register.getReg().getGenderDropDown(),
						register.getCbp().getGrooming(), register.getCbp().getObedience(),
						register.getCbp().getSocialization(), register.getCbp().getFetch(),
						register.getDnDImagePanel().getImageIcon());
				// contest.
				// ActualDog x = new ActualDog(register.getReg(), register.getCbp(),
				// register.getDnDImagePanel());
				currentContest.addDog(register.getReg().getNameField().getText(),
						register.getReg().getIdField().getText(), register.getReg().getOwnerField().getText(),
						register.getReg().getGenderDropDown(), register.getCbp().getGrooming(),
						register.getCbp().getObedience(), register.getCbp().getSocialization(),
						register.getCbp().getFetch(), register.getDnDImagePanel().getImageIcon());
				register.getReg().addOne();
				register.getReg().setNameField("Enter name");
				register.getReg().setIdField(Integer.toString(currentContest.getDogCount()));
				register.getReg().setGenderDropDown(0);
				register.getReg().setOwnerField("Enter Owner's Name");
				register.getCbp().reset();
				register.resetDndImagePanel();

			}
//			try {
//				UIManager.setLookAndFeel(new NimbusLookAndFeel());
//			} catch (Exception exp) {
//				System.out.println("Error setting UI look and feel!");
//				System.exit(0);
//			}
			// register.resetDndImagePanel();

			// repaint();
			break;

		case "LOGIN":

			if (o instanceof JButton) {
				if (auth.hasAccount(regLogin) && auth.getPasswordFor(regLogin).equals(regPasswd)) {
					CardLayout layout = (CardLayout) buttonPanel.getLayout();
					layout.show(buttonPanel, "4Buttons");
				} else {
					JOptionPane.showMessageDialog(this, loginError, "Login/Password", JOptionPane.ERROR_MESSAGE);
					authButtons.reset();

				}
			}
			break;

		case "Create":
			if (o instanceof JButton) {
				if (!auth.hasAccount(regLogin)) {
					auth.putPassword(regLogin, regPasswd);
					auth.commit();
					authButtons.reset();
				} else {
					JOptionPane.showMessageDialog(this, "Account Exists. Please login!", "Login/Password",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		// case "PASSWORD":

		// if (o instanceof JPasswordField) {
		// JPasswordField tf = (JPasswordField) o;
		// char[] password = tf.getPassword();
		// System.out.println("Password field: " + new String(password));
		// }
		// break;
		case "Records":
			if (o instanceof JButton) {
				records.clearField();
				CardLayout layout = (CardLayout) informationPanel.getLayout();
				layout.show(informationPanel, "Records");

				updateTable();
			}

			break;

		case "Register":
			if (o instanceof JButton) {

				CardLayout layout = (CardLayout) informationPanel.getLayout();
				layout.show(informationPanel, "Register");
			}
			break;

		case "Contest View":

			if (o instanceof JButton) {

				CardLayout layout = (CardLayout) informationPanel.getLayout();
				layout.show(informationPanel, "Contest View");
				contest.updateDogNames();

			}
			break;

		case "Exit":

			if (o instanceof JButton) {

				authButtons.reset();
				CardLayout layout = (CardLayout) informationPanel.getLayout();

				layout.show(informationPanel, "TITLE");

				CardLayout layout1 = (CardLayout) buttonPanel.getLayout();
				layout1.show(buttonPanel, "AUTH");
				regLogin = "";
				regPasswd = "";

			}
			break;

		case "New":
		default:
			break;
		}

	}

	private void updateTable() {

		ArrayList<ActualDog> x = currentContest.getDogList();

		DefaultTableModel model1 = (DefaultTableModel) recordsPanel.getTable().getModel();
		recordsPanel.getTable().getTableHeader().setReorderingAllowed(false);
		for (int i = 0; i < recordsPanel.getTable().getColumnCount(); i++)
			recordsPanel.getTable().getColumnModel().getColumn(i).setResizable(false);

		model1.setRowCount(0);

		Object[] data = new Object[9];
		for (int i = 0; i < x.size(); i++) {
			data[0] = x.get(i).getName();
			data[1] = x.get(i).getId();
			data[2] = x.get(i).getGender();

			data[3] = x.get(i).getOwner();
			if (x.get(i).getgScore() == -1)
				data[4] = "N/A";
			else
				data[4] = x.get(i).getgScore();
			if (x.get(i).getoScore() == -1)
				data[5] = "N/A";
			else
				data[5] = x.get(i).getoScore();
			if (x.get(i).getsScore() == -1)
				data[6] = "N/A";
			else
				data[6] = x.get(i).getsScore();
			if (x.get(i).getfScore() == -1)
				data[7] = "N/A";
			else
				data[7] = x.get(i).getfScore();

			ContestantIcon img = x.get(i).getImage();
			data[8] = img;
			model1.addRow(data);
		}

		recordsPanel.repaint();
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		Object o = e.getSource();
		if (o instanceof JPasswordField) {
			JPasswordField pf = (JPasswordField) o;
			regPasswd = new String(pf.getPassword());
			System.out.println(regPasswd);
		} else if (o instanceof JTextField) {
			JTextField tf = (JTextField) o;
			regLogin = tf.getText();
			System.out.println(regLogin);
		}
	}

}