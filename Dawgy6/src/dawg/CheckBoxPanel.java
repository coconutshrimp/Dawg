/**
 * 
 */
package dawg;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * @author jammavi
 *
 */
public class CheckBoxPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox grooming, obedience, socialization, fetch;
	private ControllingFrame controller;

	public class DogInfo {
		String name;
		
	}
	 
	
	public CheckBoxPanel() {
		addComponents();
	}


	private void addComponents() {
		Font font = new Font("Arial", Font.PLAIN, 16);
		controller = ControllingFrame.getInstance();
		FlowLayout layout = new FlowLayout();
		setLayout(layout);

		grooming = new JCheckBox("Grooming", false);
		grooming.setFont(font);
		grooming.addActionListener(controller);

		obedience = new JCheckBox("Obedience", false);
		obedience.setFont(font);
		obedience.addActionListener(controller);

		socialization = new JCheckBox("Socialization", false);
		socialization.setFont(font);
		socialization.addActionListener(controller);

		fetch = new JCheckBox("Fetch", false);
		fetch.setFont(font);
		fetch.addActionListener(controller);

		JLabel contest = new JLabel("Categories: ", JLabel.LEFT);
		contest.setLabelFor(this);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(controller);
		saveButton.setActionCommand("Save");

		add(contest);
		add(grooming);
		add(Box.createHorizontalStrut(10));
		add(obedience);
		add(Box.createHorizontalStrut(10));
		add(socialization);
		add(Box.createHorizontalStrut(10));
		add(fetch);
		add(Box.createHorizontalStrut(250));
		add(saveButton);
	}


	public boolean getFetch() {
		return fetch.isSelected();
	}
	
	public void reset() {
		fetch.setSelected(false);
		grooming.setSelected(false);
		obedience.setSelected(false);
		socialization.setSelected(false);
	}
	
	public boolean getGrooming() {
		return grooming.isSelected();
	}
	
	public boolean getObedience() {
		return obedience.isSelected();
	}
	
	public boolean getSocialization() {
		return socialization.isSelected();
	}
	



	
	
}