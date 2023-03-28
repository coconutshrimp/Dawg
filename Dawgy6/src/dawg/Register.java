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

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import config.ConfigurationParameters;

/**
 * @author jammavi
 *
 */
public class Register extends JPanel {
	private static final long serialVersionUID = 1L;
	private ControllingFrame controller;
	private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;

	private JTextField nameField, idField, ownerField;
	private ActualDog dog;

	String[] genders = new String[] { "Gender", "Male", "Female" };
	private JComboBox<String> genderDropDown;

	
	private String name, id, owner, gender;
	private boolean g, o, s, f;
	
	public Register() {
		this.controller = ControllingFrame.getInstance();
		// setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		addComponents();
	}
	
	
	
	
	


	private void addComponents() {
		// JPanel dndPanel = new DnDImagePanel(controller, "GhoseImageTemplate.png");

		GroupLayout layout = new GroupLayout(this);

		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel label1 = new JLabel("Name: ", JLabel.CENTER);
		label1.setLabelFor(nameField);

		JLabel label2 = new JLabel("ID: ", JLabel.CENTER);
		label2.setLabelFor(idField);

		JLabel label3 = new JLabel("Gender: ", JLabel.CENTER);
		label3.setLabelFor(genderDropDown);

		JLabel label4 = new JLabel("Owner: ", JLabel.CENTER);
		label4.setLabelFor(ownerField);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(controller);
		saveButton.setActionCommand("Save");

		nameField = new JTextField("Enter name", 10);
		nameField.addActionListener(controller);
		
		
		
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				if (nameField.getText().equals("Enter name")) {
					nameField.setText("");
				}
				nameField.setForeground(Color.BLACK);
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (nameField.getText().equals("")) {
					nameField.setText("Enter name");
				}
				nameField.setForeground(Color.BLACK);

			}
		});
		
		

		idField = new JTextField("Enter ID", 10);
		idField.addActionListener(controller);
		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				if (idField.getText().equals("Enter ID")) {
					idField.setText("");
				}
				idField.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (idField.getText().equals("")) {
					idField.setText("Enter ID");
				}
				idField.setForeground(Color.BLACK);

			}
		});


		genderDropDown = new JComboBox<>(genders);
		
		genderDropDown.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				genderDropDown.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				genderDropDown.setForeground(Color.BLACK);				
			}
		});

		ownerField = new JTextField("Enter Owner's Name", 10);
		ownerField.addActionListener(controller);
		ownerField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				if (ownerField.getText().equals("Enter Owner's Name")) {
					ownerField.setText("");
				}
				ownerField.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (ownerField.getText().equals("")) {
					ownerField.setText("Enter Owner's Name");
				}
				ownerField.setForeground(Color.BLACK);

			}
		});
		JButton button = new JButton("Finalize Info");
		
		
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				// .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				// .addComponent(dndPanel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label1)
						.addComponent(label2).addComponent(label3).addComponent(label4))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(nameField)
						.addComponent(idField).addComponent(genderDropDown).addComponent(ownerField))

		);
		layout.setVerticalGroup(layout.createSequentialGroup().addGap(30)
				// .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				// .addComponent(dndPanel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1)
						.addComponent(nameField))
				.addGap(15)
				.addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2).addComponent(idField))
				.addGap(15)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label3)
						.addComponent(genderDropDown))
				.addGap(15).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label4)
						.addComponent(ownerField))
				
				.addGap(15)

		);

	}




	public ActualDog getDog() {
		return dog;
	}


	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(String name) {
		this.nameField.setText(name);
	}

    public void setNameRed() {
    	nameField.setSelectionStart(0);
    	nameField.setSelectionEnd(nameField.getText().length());
    	nameField.setForeground(Color.RED);;
    }
	public JTextField getIdField() {
		return idField;
	}



	public void setIdField(String id) {
		this.idField.setText(id);
	}


	public void setIDRed() {
    	idField.setSelectionStart(0);
    	idField.setSelectionEnd(nameField.getText().length());
    	idField.setForeground(Color.RED);;
    }
	
	public JTextField getOwnerField() {
		return ownerField;
	}


	public void setOwnerField(String owner) {
		ownerField.setText(owner);
	}
	
	public void setOwnerRed() {
    	ownerField.setSelectionStart(0);
    	ownerField.setSelectionEnd(nameField.getText().length());
    	ownerField.setForeground(Color.RED);;
    }

	public String getGenderDropDown() {
		return (String) genderDropDown.getSelectedItem();
	}

	public void setGenderDropDown() {
		genderDropDown.setSelectedIndex(0);
	}
	
	public void setGenderRed() {
		
    	genderDropDown.setForeground(Color.RED);
    }



}