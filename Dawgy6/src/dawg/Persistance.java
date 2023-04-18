/**
 * 
 */
package dawg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * @author Krish Pillai
 *
 */
public class Persistance {
	
	public static void commit (Serializable object, 
			String path) throws FileNotFoundException
		{
		URL dbURL = Persistance.class.getResource(path);
		if (dbURL == null) {
			throw new FileNotFoundException("Could not locate " + path + "!");
		}
		File file = null;
		try {
			file = new File(dbURL.toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {			
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}	
	
	public static Serializable restore(String path) {
		java.net.URL dbURL = Persistance.class.getResource(path);

		File file = null;
		try {
			file = new File(dbURL.toURI());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Serializable object = null;
		try {
			fis = new FileInputStream(file);
			int n = fis.available();
			if (n > 0) {

				ois = new ObjectInputStream(fis);
				object = (Serializable) ois.readObject();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());

		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} 
		return object;
	}

}
