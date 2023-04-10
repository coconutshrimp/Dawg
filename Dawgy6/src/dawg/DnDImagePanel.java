package dawg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.lang.module.Configuration;

import config.ConfigurationParameters;

public class DnDImagePanel extends BasicImagePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;
	public DnDImagePanel(ControllingFrame controller, String imageFile) {
		this(controller);
		setPreferredSize(new Dimension(WIDTH/3, HEIGHT/3));
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

}
