package dawg;

import javax.swing.SwingUtilities;
/**
 * 
 *
 */
public class DawgShow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(()->ControllingFrame.getInstance("Test Demo"));
	}

	public static String findCallingMethod() {
		StringBuilder sb = new StringBuilder();
		StackTraceElement[] sa = new Exception().getStackTrace();
		for (StackTraceElement e : sa) {
			if (e.getMethodName().equals("findCallingMethod"))
				continue;
			sb.append("Line:" + e.getLineNumber() + "," + e.getClassName()+":"+ e.getMethodName() + "<-");
		}
		String[] chain = sb.toString().split(" ");
		
		if (chain.length > 3)
			return chain[1] + "<-" + chain[2] + "<-" + chain[3];
		else
			return sb.toString();
	}
}
