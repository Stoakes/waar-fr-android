package fr.waar.android.lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encrypter {
	
	public static String encryptMD5(String str)
	{

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(str.getBytes());
		byte[] md5 = md.digest();
		
		str = new String(md5);
		
		return str;
	}

}
