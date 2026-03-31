/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JasyptConfig {

	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("monkey");
		config.setPoolSize(1);
		encryptor.setConfig(config);
		return encryptor;
	}

	public static void main(String[] args){

		String plainText = "hera237!";  // 암호화할 문자

		// 설정
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("monkey");
		config.setPoolSize(1);
		encryptor.setConfig(config);


		// 테스트
		String encryptText = encryptor.encrypt(plainText);
        log.info("encryptText: {}", encryptText);
        log.info("encryptor.decrypt(encryptText): {}", encryptor.decrypt(encryptText));
        System.out.println(encryptText);
        
        String decryptText = encryptor.decrypt("8z/0HjWxtjhC0fQ3XqYNb664cZ5rfpiI");
        System.out.println(decryptText);
        

	}

}
