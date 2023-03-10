package test;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {
	
	public static void main(String[] args) throws Exception {
		// 明文
		String plainTest = "Hello World!";
		System.out.println("明文: " + plainTest);
		// 產生密鑰
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		SecretKey secretKey = keyGen.generateKey();
		// 使用密鑰加密明文
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrytedData = cipher.doFinal(plainTest.getBytes());
		System.out.println("使用密鑰加密明文: " + encrytedData);
		String encrytedBase64Data = Base64.getEncoder().encodeToString(encrytedData);
		System.out.println("使用密鑰加密明文(Base64): " + encrytedBase64Data);
		System.out.println("---------------------------------------------");
		// 儲存
		// 將 encrytedBase64Data 存入資料庫
		// 使用密鑰解密明文
		System.out.println("從資料庫拿到(Base64):" + encrytedBase64Data); // 得到 Base64 字串
		byte[] encryted = Base64.getDecoder().decode(encrytedBase64Data); 
		System.out.println("得到加密密文: " + encryted);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] dncryptedData = cipher.doFinal(encryted);
		System.out.println("得到明文:" + new String(dncryptedData));
	}
	
}
