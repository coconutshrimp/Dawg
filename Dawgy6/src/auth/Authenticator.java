/**
 * 
 */
package auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyStore.Entry;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author pillai
 *
 */
public class Authenticator implements Serializable {

	private static final Scanner keyboard = new Scanner(System.in);

	/**
	 * 
	 */
	private static final long serialVersionUID = 2793711677269935785L;
	/**
	 * Authenticator
	 */
	private String authFile = "/db/auth.db";
	private HashMap<String, byte[]> passwordMap = new HashMap<>();
	private SecretKey key;
	private String algorithms = "DES/ECB/PKCS5Padding";
	private String algorithmInUse = "DES";

	public Authenticator() {
		if (!loadAuthParameters()) {
			passwordMap = new HashMap<>();
			try {
				key = new SecretKeyGenerator(algorithmInUse).getSecretKey();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
	}

	public void putPassword(String login, String password) {
		byte[] passwd = password.trim().getBytes();
		try {
			Encryptor encrypt = new Encryptor(passwd, algorithms, key);
			passwd = encrypt.getCipherText();
		} catch (InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		passwordMap.put(login, passwd);
	}

	public String getPasswordFor(String login) {
		if (!hasAccount(login)) {
			return null;
		}
		byte[] passwd = passwordMap.get(login);

		try {
			Decryptor decrypt = new Decryptor(passwd, algorithms, key);
			return new String(decrypt.getClearText());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasAccount(String login) {
		return passwordMap.containsKey(login);
	}
	
//	public boolean hasPassword(String login, String password) {
//		Set<String> keySet = passwordMap.keySet();
//		for (String e: keySet) {
//			String passWordEntry = getPassword(e);
//			if (password.equals(passWordEntry))
//				return true;
//		}
//		return false;
//	}

	public void commit() {
		URL authURL = Authenticator.class.getResource(authFile);
		if (authURL == null) {
			System.err.println("Could not locate " + authFile + " in /db/ package!");
			System.err.println("Commit failed!");
			return;
		}
		File file = null;
		try {
			file = new File(authURL.toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {			
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// recovery
		} 
	}

	public boolean loadAuthParameters() {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Authenticator auth = null;
		URL url = Authenticator.class.getResource(authFile);

		try {
			fis = new FileInputStream(new File(url.toURI()));
			ois = new ObjectInputStream(fis);
			auth = (Authenticator) ois.readObject();
		} catch (IOException e) {
			System.err.println("Nothing to read!");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
		if (auth != null) {
			this.passwordMap = auth.passwordMap;
			this.key = auth.key;
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
//		Authenticator auth = new Authenticator();
//		boolean done = false;
//		String resp = "";
//		do {
//			System.out.print("Enter login: ");
//			String login = keyboard.nextLine();
//			System.out.println("Checking if login exists...");
//			if (auth.hasPassword(login)) {
//				System.out.println("Entry exists!");
//				System.out.println("Password is: " + auth.getPassword(login));
//				System.out.print("Overwrite password? (y/n) ");
//				resp = keyboard.nextLine();
//				if (resp.equalsIgnoreCase("y")) {
//					System.out.print("Enter password: ");
//					String password = keyboard.nextLine();
//					auth.putPassword(login, password);
//					System.out.println("Updated!");
//				}
//			} else {
//				System.out.println("No password entry!");
//				System.out.print("Enter password: ");
//				String password = keyboard.nextLine();
//				System.out.println("Adding password and login...");
//				auth.putPassword(login, password);
//				System.out.println("done!");
//			}
//			System.out.print("Enter another login? (y/n) ");
//			resp = keyboard.nextLine();
//			if (!resp.equalsIgnoreCase("y")) {
//				done = true;
//			}
//		} while (!done);
//		System.out.print("Commit password file? (y/n)");
//		resp = keyboard.nextLine();
//		if (resp.equalsIgnoreCase("y")) {
//			auth.commit();
//			System.out.println("Committed!");
//		}
//		System.out.println("Bye!");
//	
		}

}
