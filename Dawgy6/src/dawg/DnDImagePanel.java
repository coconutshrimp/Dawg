package dawg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.module.Configuration;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import config.ConfigurationParameters;

public class DnDImagePanel extends BasicImagePanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Image sourceImage;
	private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;

	public DnDImagePanel(ControllingFrame controller, String imageFile) {
		this(controller);
		setPreferredSize(new Dimension(WIDTH / 3, HEIGHT / 3));
		setImage(getImageFromPackage(imageFile));
	}

	public DnDImagePanel(ControllingFrame controller) {
		super(controller);

		setTransferHandler(new ImageTransferHandler(this));
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	public Dimension getImageDimension() {
		return new Dimension(image.getWidth(null), image.getHeight(null));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String separator = System.getProperty("file.separator");
		String path = System.getProperty("user.dir"); // needs to go to mavins code
//			+ separator + "src" + separator + "images" + separator;

		JFileChooser chooser = new JFileChooser(path);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG & GIF images", "jpg", "gif", "png",
				"jpeg");
		chooser.setFileFilter(filter);

		int returnValue = chooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			String selectedFile = chooser.getCurrentDirectory().toPath() + separator
					+ chooser.getSelectedFile().getName().toString();
			setImage(loadImage(selectedFile));

		}
	}

	public Image loadImage(String selectedFile) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(new File(selectedFile));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		try {
			setImage(ImageIO.read(fis));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		repaint();
		return getImage();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public ContestantIcon getImageIcon() {
		return new ContestantIcon(getImage());
	}
}
