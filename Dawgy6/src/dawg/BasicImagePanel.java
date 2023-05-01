/**
 * 
 */
package dawg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.StackWalker.StackFrame;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import config.ConfigurationParameters;

/**
 *
 */
public class BasicImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected Image image, defaultImage;
	private ControllingFrame controller;
	private final String defaultFile = "images/GhoseImageTemplate.png";

	public BasicImagePanel(ControllingFrame controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		defaultImage = getImageFromPackage(defaultFile);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		Image img = null;
		if (image == null) {
			img = letterboxImage(defaultImage, getWidth(), getHeight());
		} else {
			img = letterboxImage(image, getWidth(), getHeight());
		}
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}

	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imageURL = BasicImagePanel.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return new ImageIcon(imageURL, description);
		}
	}

	protected BufferedImage getImageFromPackage(String path) {

		URL url = this.getClass().getClassLoader().getResource(path);
		BufferedImage image = null;
		try {
			if (url == null) {
				System.err.println("Can't find image resource file: \"" + path + "\"");
				System.exit(0);
			}
			// use ImageIO to read the image in
			image = ImageIO.read(url);
			if (image == null)
				throw new NullPointerException("Could not read image contents from \"" + path + "\"");
		} catch (IOException e) {
			System.err.println("Failed to load: \"" + path + "\"");
		}
		return image;
	}

	public void setImage(Image image) {
		if (image != null) {
			this.image = image;
		}
	}

	public void setImageToDefault() {
		this.image = null;
	}

	public ControllingFrame getController() {
		return controller;
	}

	public Image getImage() {
		if (image == null)
			return defaultImage;
		else
			return image;
	}

	public Image getCurrentImage(String path) {
		return image;
	}

	public void resetImage() {
		image = null;
	}


	public boolean imageChanged() {
		return image != null;
	}

	public Image letterboxImage(Image srcImage, int panelWidth, int panelHeight) {
		BufferedImage img = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics gc = img.getGraphics();

		double scaleFactor = 1.0;

		int imageWidth = srcImage.getWidth(null);
		int imageHeight = srcImage.getHeight(null);

		// Scale by the longer edge
		if (imageWidth > imageHeight) {
			scaleFactor = panelWidth / (double) imageWidth;
		} else {
			scaleFactor = panelHeight / (double) imageHeight;
		}

		int sx1 = 0;
		int sy1 = 0;
		int sx2 = imageWidth;
		int sy2 = imageHeight;

		int dx1 = (panelWidth - (int) (imageWidth * scaleFactor)) / 2;
		int dy1 = (panelHeight - (int) (imageHeight * scaleFactor)) / 2;
		int dx2 = (panelWidth + (int) (imageWidth * scaleFactor)) / 2;
		int dy2 = (panelHeight + (int) (imageHeight * scaleFactor)) / 2;

		gc.setColor(getBackground());
		gc.fillRect(0, 0, panelWidth, panelHeight);
		gc.drawImage(srcImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		gc.dispose();
		return img;
	}

}
