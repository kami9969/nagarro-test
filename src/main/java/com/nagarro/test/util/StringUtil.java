package com.nagarro.test.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringUtil {

	public static String hashWith256(String textToHash) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] byteOfTextToHash = textToHash.getBytes(StandardCharsets.UTF_8);
			byte[] hashedByetArray = digest.digest(byteOfTextToHash);
			String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
