/**
 * 
 */
package dawg;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

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
	private JPanel buttonPanel;
	private String loginError = "<html>Wrong <u>Username</u> and <u>Password</u> combination!<br>&nbsp;&nbsp;&nbsp;&nbsp; Try again!</html>",
			pictureError = "No Image!";
	
	private String categoryError = "<html>Please select at least one category!</html>";
	
	private String regLogin, regPasswd;

	private StartUpScreen authScreen;
	private ButtonPanelForAuthentication authButtons;
	private FourButtons fourButtons;
	private ContestView contest;
	private RegisterContainer register;
	private Authenticator auth;
	private Records records;
	private boolean FinalizeRegister;
	private static RecordsPanel table;
	private ContestsClass contestclass;

	private static ControllingFrame instance = null;

	private ControllingFrame(String title) {
		// initial setup
		auth = new Authenticator();

		instance = this;
		setBackground(Color.BLACK);
		setTitle(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	public static ControllingFrame getInstance() {
		return getInstance("Demo");
	}

	public static void setSelectedContest(String contest) {
		CardLayout layout = (CardLayout) informationPanel.getLayout();
		layout.show(informationPanel, "Table");
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

		authScreen = new StartUpScreen(this, "images/dog_logo.png");
		contest = new ContestView();
		register = new RegisterContainer(this);
		records = new Records();
		table = new RecordsPanel();

		informationPanel.add(authScreen, "TITLE");
		informationPanel.add(contest, "Contest View");
		informationPanel.add(register, "Register");
		informationPanel.add(records, "Records");
		informationPanel.add(table, "Table");

		containerPanel.add(informationPanel, BorderLayout.CENTER);

		authButtons = new ButtonPanelForAuthentication();
		fourButtons = new FourButtons();
		buttonPanel.add(authButtons, "AUTH");
		buttonPanel.add(fourButtons, "4Buttons");
		containerPanel.add(buttonPanel, BorderLayout.LINE_END);

		add(containerPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		Object o = null;
		o = e.getSource();
		switch (actionCommand) {

		case "Save":
			JButton saveButton = (JButton) o;
			FinalizeRegister = true;
			if (register.getReg().getNameField().getText().equalsIgnoreCase("Enter name")) {
				register.getReg().setNameRed();
				FinalizeRegister = false;

			}
			if (register.getReg().getIdField().getText().equalsIgnoreCase("Enter ID")) {
				register.getReg().setIDRed();
				FinalizeRegister = false;
			}
			if (register.getReg().getGenderDropDown().equalsIgnoreCase("Gender")) {
				register.getReg().setGenderRed();
				FinalizeRegister = false;
			}
			if (register.getReg().getOwnerField().getText().equalsIgnoreCase("Enter Owner's Name")) {
				register.getReg().setOwnerRed();
				FinalizeRegister = false;
			}
			
			
			boolean x1, x2, x3, x4;
			
			x1 = register.getCbp().getFetch();
			x2 = register.getCbp().getGrooming();
			x3 = register.getCbp().getSocialization();
			x4 = register.getCbp().getObedience();
			System.out.println(x1);
			System.out.println(x2);
			System.out.println(x3);
			System.out.println(x4);
			if(x1 == false && x2 == false && x3 == false && x4 == false)
			{
				JOptionPane.showMessageDialog(this, categoryError, "Error", JOptionPane.ERROR_MESSAGE);
			}

			DnDImagePanel imgPanel = register.getDnDImagePanel();

			System.out.println(imgPanel.getImage().hashCode());
//			if (!imgPanel.imageChanged()) {
//				JOptionPane.showMessageDialog(this, pictureError, "Picture", JOptionPane.ERROR_MESSAGE);
//				FinalizeRegister = false;
//			}
//			
			

			if (FinalizeRegister) {
				contest.addDog(register.getReg().getNameField().getText(), register.getReg().getIdField().getText(), register.getReg().getOwnerField().getText(), 
						register.getReg().getGenderDropDown(), register.getCbp().getGrooming(), register.getCbp().getObedience(), register.getCbp().getSocialization(),
						register.getCbp().getFetch());
				//contest.
				//ActualDog x = new ActualDog(register.getReg(), register.getCbp(), register.getDnDImagePanel());\
				register.getReg().addOne();
				register.getReg().setNameField("Enter name");
				register.getReg().setIdField(Integer.toString(register.getReg().getIDcount()));
				register.getReg().setGenderDropDown(0);
				register.getReg().setOwnerField("Enter Owner's Name");
				register.getCbp().reset();
				


			}
			try {
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
			} catch (Exception exp) {
				System.out.println("Error setting UI look and feel!");
				System.exit(0);
			}
			register.resetDndImagePanel();
			repaint();
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
				CardLayout layout = (CardLayout) informationPanel.getLayout();
				layout.show(informationPanel, "Records");
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
			if (o instanceof JButton) {

				System.out.println("Hooray");
			}
			break;
		}

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
