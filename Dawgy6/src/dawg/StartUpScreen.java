/**
 * 
 */
package dawg;

import config.ConfigurationParameters;

/**
 * @author lopmilc
 *
 */
public class StartUpScreen extends BasicImagePanel {

	private final int WIDTH = ConfigurationParameters.width, HEIGHT = ConfigurationParameters.height;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public StartUpScreen(ControllingFrame controller, String imageFile) {
		super(controller);
		setImage(getImageFromPackage(imageFile));
	}
	

}
