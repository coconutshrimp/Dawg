package dawg;

import javax.swing.SwingUtilities;
/**
 * @author lopmilc
 *
 */
public class DawgShow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->ControllingFrame.getInstance("Test Demo"));
	}

}
