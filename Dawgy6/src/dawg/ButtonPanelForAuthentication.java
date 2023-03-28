/**
 * 
 */
package dawg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author lopmilc
 *
 */
public class ButtonPanelForAuthentication extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllingFrame controller;
	private final int WIDTH = 250, HEIGHT = 500;
	private JPasswordField passwordField;
	private JTextField loginField;

	public ButtonPanelForAuthentication() {
		this.controller = ControllingFrame.getInstance();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		addComponents();
	}

	private void addComponents() {
		// setBackground(Color.BLACK);
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel label1 = new JLabel("Username: ", JLabel.CENTER);
		label1.setLabelFor(loginField);

		JLabel label2 = new JLabel("Password: ", JLabel.CENTER);
		label2.setLabelFor(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(controller);
		loginButton.setActionCommand("LOGIN");

		JButton createAccount = new JButton("Create");
		createAccount.addActionListener(controller);

		loginField = new JTextField("jdoe@email.org");
		// loginField.selectAll();

		// String username = loginField.getText();

		loginField.setBounds(100, 20, 165, 25);
		loginField.setActionCommand("LOGIN");
		loginField.addActionListener(controller);
		loginField.addFocusListener(controller);
		
		
		
		loginField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				loginField.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		//loginField.setFocusTraversalKeysEnabled(f);

		passwordField = new JPasswordField(10);
		passwordField.setActionCommand("PASSWORD");
		// char[] password = passwordField.getPassword();
		passwordField.addActionListener(controller);
		passwordField.addFocusListener(controller);
		//passwordField.setFocusTraversalKeysEnabled(true);

		// Lay out everything.

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label1)
						.addComponent(label2).addComponent(loginButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(loginField)
						.addComponent(passwordField).addComponent(createAccount)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1)
						.addComponent(loginField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2)
						.addComponent(passwordField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(loginButton)
						.addComponent(createAccount)));
	}

	public void reset() {
		
			loginField.setText("jdoe@email.org");
		passwordField.setText("");
	
	}
}
