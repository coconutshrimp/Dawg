/**
 * 
 */
package dawg;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import config.ConfigurationParameters;

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
	public int hashcode;
    private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;

	public RegisterContainer(ControllingFrame controller) {
		this.controller = controller;
		reg = new Register();

		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		dnd = new DnDImagePanel(controller, "images/GhoseImageTemplate.png");
		dnd.setPreferredSize(new Dimension(WIDTH/3, HEIGHT/3));
        reg = new Register();
        cbp = new CheckBoxPanel();
        DnDImagePanel advert = new DnDImagePanel(controller, "images/dawgfoodad.png");
        advert.setPreferredSize(new Dimension(WIDTH/3, HEIGHT/7));
		add(dnd);
		add(reg);
		
		add(cbp);
		add(advert);
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
	
	  public void resetDndImagePanel() {
          dnd.resetImage();
          
  }


}
