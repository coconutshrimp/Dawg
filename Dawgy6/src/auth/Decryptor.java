package auth;
//Author: Krish Pillai
import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decryptor {
    // get parameter object for password-based encryption
	private byte[] clearText;
	
	//Constructor
    public Decryptor(byte[] cipherText, String alg, SecretKey key)
    throws NoSuchAlgorithmException,
    NoSuchPaddingException, InvalidKeyException 
    {
    	// get cipher object for password-based encryption
    	Cipher cipher = Cipher.getInstance(alg);
    	
    	// initialize cipher for decryption
    	// uses the initialized algParams from above
    	cipher.init(Cipher.DECRYPT_MODE, key);
    	try {

			clearText = cipher.doFinal(cipherText);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public byte[] getClearText() {
    	return clearText;
    }
    
    public String toString() {
    	return new String(clearText);
    }
} //EOF