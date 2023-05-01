package dawg;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ContestantIcon extends ImageIcon implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ContestantIcon(Image image) {
		setImage(resize(image, 64, 64));
	}
	
	public ContestantIcon(String path) {
		try {
			URL url = ContestantIcon.class.getResource(path);
			Image image = ImageIO.read(url);
			setImage(image);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			;
		}
	}

	private Image resize(Image image, int width, int height) {
		BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics gc = temp.getGraphics();
		gc.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null), image.getHeight(null), null);
		gc.dispose();
		return (Image) temp;

	}
}
