package cjfw.core.email.utility;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.17
 * @description : smtp mail 전송시 사용자의 인증정보 처리 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.17        sungyeon.lee       생성
 */
public class UserAuthentication extends Authenticator {

	private PasswordAuthentication pa;
	
	/**
	 * smtp mail전송시 사용자의 id, password 인증정보 처리
	 */
	/**
	 * 
	 * @description : UserAuthentication의 생성자
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public UserAuthentication(String id, String password) {
		pa = new PasswordAuthentication(id, password);
	}

	/**
	 * 
	 * @overridden  : @see jakarta.mail.Authenticator#getPasswordAuthentication()
	 * @description : getPasswordAuthentication 기능을 Override하여 구현한 Method
	 * @issues      :
	 * -----------------------------------------------------------
	 * DATE              AUTHOR             MAJOR_ISSUE
	 * -----------------------------------------------------------
	 * 2023.10.17        sungyeon.lee       생성
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
