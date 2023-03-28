/**
 * 
 */
package dawg;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import config.ConfigurationParameters;

/**
 * @author jammavi
 *
 */
public class FourButtons extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControllingFrame controller;
	private final int WIDTH = ConfigurationParameters.width / 4, HEIGHT = ConfigurationParameters.height / 4;

	public FourButtons() {
		this.controller = ControllingFrame.getInstance();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder());

		addComponents();
	}

	private void addComponents() {
		//setBackground(Color.BLUE);
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		Dimension largeSize = new Dimension(WIDTH, HEIGHT);
		ImageIcon icon = BasicImagePanel.createImageIcon("/images/bluebig1.png", "Blue button");
		JButton recordsButton = new JButton("Records", icon);
		recordsButton.setBorderPainted(false);
		recordsButton.setVerticalTextPosition(SwingConstants.CENTER);
		recordsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		recordsButton.addActionListener(controller);
		recordsButton.setMaximumSize(largeSize);
		recordsButton.setActionCommand("Records");

		JButton registerButton = new JButton("Register", icon);
		registerButton.setBorderPainted(false);
		registerButton.setVerticalTextPosition(SwingConstants.CENTER);
		registerButton.setHorizontalTextPosition(SwingConstants.CENTER);
		registerButton.addActionListener(controller);
		registerButton.setMaximumSize(largeSize);
		registerButton.setActionCommand("Register");

		JButton contestButton = new JButton("Contest View", icon);
		contestButton.setBorderPainted(false);
		contestButton.setVerticalTextPosition(SwingConstants.CENTER);
		contestButton.setHorizontalTextPosition(SwingConstants.CENTER);
		contestButton.addActionListener(controller);
		contestButton.setMaximumSize(largeSize);
		contestButton.setActionCommand("Contest View");

		JButton exitButton = new JButton("Exit", icon);
		exitButton.setBorderPainted(false);
		exitButton.setVerticalTextPosition(SwingConstants.CENTER);
		exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
		exitButton.addActionListener(controller);
		exitButton.setMaximumSize(largeSize);
		exitButton.setActionCommand("Exit");

		add(recordsButton);
		add(registerButton);
		add(contestButton);
		add(exitButton);

	}

}
