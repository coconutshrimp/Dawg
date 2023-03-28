/**
 * 
 */
package dawg;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author jammavi
 *
 */
public class RegisterContainer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllingFrame controller;
	private Register reg;
	private CheckBoxPanel cbp;
	private DnDImagePanel dnd;

	public RegisterContainer(ControllingFrame controller) {
		this.controller = controller;
		reg = new Register();

		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dnd = new DnDImagePanel(controller, "GhoseImageTemplate.png");
		add(dnd);
		add(reg);
		cbp = new CheckBoxPanel();
		add(cbp);
		add(new DnDImagePanel(controller, "dawgfoodad.png"));
	}

	public Register getReg() {
		return reg;
	}

	public CheckBoxPanel getCbp() {
		return cbp;
	}

	public DnDImagePanel getDnDImagePanel() {
		return dnd;
	}

}
