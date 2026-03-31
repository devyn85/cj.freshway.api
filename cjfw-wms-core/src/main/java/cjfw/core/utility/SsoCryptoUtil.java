package cjfw.core.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : SsoCryptoUtil 기능을 구현한 Controller Class
 * 				  CJWorld Single Sign On 관련 복호화 기능
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
@Slf4j
public class SsoCryptoUtil {
	private static final String KEY_VALUE = "90fd33a9fbb56cd1"; // NOSONAR

	/**
	 * 
	 * @description : main 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public static void main(String[] args) throws Exception {
		String keyValue = "90fd33a9fbb56cd1";		
		String encodedtext = "kUGj7tXCPPmRWm2C8JYsFw==";	

		SsoCryptoUtil td = new SsoCryptoUtil();
		String decodedtext = td.decrypt(encodedtext);

        log.info("key   : {}" , keyValue);
        log.info("encrypted   : {}" , encodedtext);
        log.info("decrypted   : {}" , decodedtext);
	}

	/**
	 * 
	 * @description : generateKey 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public SecretKey generateKey(String keyValue) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest(keyValue.getBytes("utf-8"));

		byte[] keyBytes = new byte[24];
		System.arraycopy(digestOfPassword, 0, keyBytes, 0, digestOfPassword.length);
		for (int j = 0, k = 16; j < 8; k++, j++) {
			keyBytes[k] = keyBytes[j];
		}

		DESedeKeySpec key = new DESedeKeySpec(keyBytes);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

		return keyFactory.generateSecret(key);
	}

	/**
	 * 
	 * @description : decrypt 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.22        sungyeon.lee       생성
	 */
	public String decrypt(String encryptedStr) throws Exception {
		byte[] message = Base64.decodeBase64(encryptedStr);

		SecretKey key = generateKey(KEY_VALUE);
		final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding"); // NOSONAR
		decipher.init(Cipher.DECRYPT_MODE, key);

		final byte[] plainText = decipher.doFinal(message);
		String decodedtext = new String(plainText, "UTF-8");

		return decodedtext.substring(0, decodedtext.length()-7);
	}
}
