package test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;

public class Sha384Example {
	
	public static void main(String[] args) {
		getHex("hello.js");
	}
	
	public static String getHex(String fileName) {
		String filePath = "C:\\Users\\a0909\\OneDrive\\桌面\\資料區\\security_coding\\WebSecure\\src\\main\\webapp\\csp\\" + fileName;
		String algorithm = "SHA-384";
		String hash = null;
		
		try {
			byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] hashBytes = md.digest(fileBytes);
			hash = Base64.getEncoder().encodeToString(hashBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(hash);
		return hash;
	}
}
