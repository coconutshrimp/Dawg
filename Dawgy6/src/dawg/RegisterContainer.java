/**
 * 
 */
package dawg;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private BufferedImage sourceImage;
	public int hashcode;
	private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;

	public RegisterContainer(ControllingFrame controller) {
		this.controller = controller;

		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		dnd = new DnDImagePanel(controller, "images/GhoseImageTemplate.png");
		
		dnd.setPreferredSize(new Dimension(WIDTH/3, HEIGHT/3));

		reg = new Register();
		cbp = new CheckBoxPanel();
		DnDImagePanel advert = new DnDImagePanel(controller, "images/dawgfoodad.png");
		advert.setPreferredSize(new Dimension(WIDTH/3, HEIGHT/7));
		
		add(dnd);
		dnd.addMouseListener(dnd); //needs added to mavin's code
		//dnd.letterboxImage(sourceImage, dnd.getWidth(), dnd.getHeight()); //not getting correct width and height. why? I dont know
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
		dnd.invalidate();
		repaint();
	}

}
