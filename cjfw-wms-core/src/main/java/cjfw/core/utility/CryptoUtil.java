package cjfw.core.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cjfw.core.exception.UserHandleException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : CryptoUtil 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Slf4j
public class CryptoUtil {
	
	/**
	 * 
	 * @description : CryptoUtil의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	private CryptoUtil() {}
	
	/**
	 * 
	 * @description : encrypt 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public static String encrypt(String planText) {
		try {
			String plainSaltPassword = "";
			String plainPassword = SHA256(planText);
			plainSaltPassword = SHA256_SALT(plainPassword);
			return plainSaltPassword;
		} catch (Exception e) {
			throw new UserHandleException(e.toString());
		}
	}
	
	/**
	 * 
	 * @description : SHA256 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public static String SHA256(String text){
		String sha256Text = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			// 평문+salt 암호화
			md.update(text.getBytes());
			sha256Text = String.format("%064x", new BigInteger(1, md.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
            log.error("에러발생", e);
		}
		
		return sha256Text;
	}
	
	/**
	 * 
	 * @description : SHA256_SALT 기능을 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.20        sungyeon.lee       생성
	 */
	public static String SHA256_SALT(String text){
		String sha256Text = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			String salt = "CANALFRAME_SHA_SALT_KEY";
			// 평문+salt 암호화
			md.update(salt.getBytes());
			md.update(text.getBytes());
			sha256Text = String.format("%064x", new BigInteger(1, md.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
            log.error("에러발생", e);
		}
		
		return sha256Text;
	}
}
