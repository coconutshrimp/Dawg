package dawg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

public class DnDImagePanel extends BasicImagePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean letterBoxed = false;

	public DnDImagePanel(ControllingFrame controller, String imageFile) {
		this(controller);
		setImageFromPackageFile("images", imageFile);
		setPreferredSize(getImageDimension());
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
	
	private BufferedImage letterboxImage(BufferedImage srcImage, 
			int panelWidth, int panelHeight) {
		BufferedImage img = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_3BYTE_BGR);
		Graphics gc = img.getGraphics();

		double scaleFactor = 1.0;		

		int imageWidth = srcImage.getWidth(null);
		int imageHeight = srcImage.getHeight(null);
		

		// Scale by the longer edge
		if (imageWidth > imageHeight) {
			scaleFactor = panelWidth/(double)imageWidth;
		} else {
			scaleFactor = panelHeight/(double)imageHeight;
		}
		
		int sx1 = 0;
		int sy1 = 0;
		int sx2 = imageWidth;
		int sy2 = imageHeight;
		
		int dx1 = (panelWidth-(int)(imageWidth*scaleFactor))/2;
		int dy1 = (panelHeight-(int)(imageHeight*scaleFactor))/2;
		int dx2 = (panelWidth+(int)(imageWidth*scaleFactor))/2;
		int dy2 = (panelHeight+(int)(imageHeight*scaleFactor))/2;
		

		gc.setColor(getBackground());
		gc.fillRect(0, 0, panelWidth, panelHeight);
		gc.drawImage(srcImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		gc.dispose();
		return img;
	}
	
	public Dimension getImageDimension() {
		return new Dimension(image.getWidth(), image.getHeight());
	}
	
	@Override
	public void paint(Graphics g) {
		if (image != null) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			if (!letterBoxed) {
				letterBoxed = true;
				image = letterboxImage(image, getWidth(), getHeight());	
			}
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}

	}
	
	@Override
	public void setImage(BufferedImage image) {
		super.setImage(letterboxImage(image, getWidth(), getHeight()));
	}
}
