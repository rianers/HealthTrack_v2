package br.com.healthtrack.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
	public static String generatePasswordHash(String password) {
		String response = null;
		MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            
            BigInteger bigInt = new BigInteger(1,digest);
            response = bigInt.toString(16);
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return response;
	}
}
