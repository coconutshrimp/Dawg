package auth;
//Author: Krish Pillai
import javax.crypto.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryptor{
	private Cipher cipher;
	private byte[] cipherText;
	
	public Encryptor(byte[] clearText, String alg, SecretKey key) 
		throws BadPaddingException, NoSuchAlgorithmException, 
		NoSuchPaddingException, 
		InvalidKeyException, IOException{
		
		// Get cipher object for specified algorithm
		this.cipher = Cipher.getInstance(alg);
		
		//Initialize cipher
		cipher.init(Cipher.ENCRYPT_MODE, key);
		try {
			cipherText = cipher.doFinal(clearText);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public byte[] getCipherText() {
		return cipherText;
	}
	
	@Override
	public String toString() {
		return new String(cipherText);
	}
} //EOF
