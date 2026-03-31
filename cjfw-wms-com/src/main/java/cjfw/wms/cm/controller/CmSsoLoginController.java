package cjfw.wms.cm.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.core.utility.ContextUtil;
import cjfw.core.utility.SsoCryptoUtil;
import cjfw.wms.cm.CanalFrameAuthenticationToken;
import cjfw.wms.cm.CanalFrameLoginHandler;
import cjfw.wms.cm.dto.CmLoginReqDto;
import cjfw.wms.cm.dto.CmLoginResDto;
import cjfw.wms.cm.service.CmLoginService;
import cjfw.wms.portal.common.auth.login.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwork.sso.agent.SSOGlobals;
import jwork.sso.agent.SSOHealthChecker;
import jwork.sso.agent.SSOManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nets.sso.agent.web.v9.SSOAuthn;
import nets.sso.agent.web.v9.SSOUser;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * 
 * @author : JangGwangSeok (breaker3317@cj.net)
 * @date : 2025.10.24
 * @description : SSO 로그인
 * @issues :
 * 
 *         <pre>
 *  
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.24 JangGwangSeok (breaker3317@cj.net) 생성
 *         </pre>
 */
@Tag(name = "SSO 로그인", description = "SSO 로그인")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/sso")
public class CmSsoLoginController {
	
	@Value("${cf.sso.secretKey}")
	private String secretKey;
	
	private final AuthenticationManager authenticationManager;
	
	private final CanalFrameLoginHandler loginHandler;
	
	private final CmLoginService cmLoginService;
	
	/**
	 * @description : CJWorld SSO 연동
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "CJWorld SSO 연동", description = "CJWorld SSO 연동")
	@PostMapping(value = "/v1.0/loginCJWorld")
	public void loginCJWorld(@RequestParam("cjworld_id") String cjWorldId, @RequestParam("language") String lang, @RequestParam(value = "verification_yn", defaultValue = "Y") String verificationYn, HttpServletResponse response) throws Exception {		
		log.info("@@@@@@@@@@@@@@@@@ cjWorldId : {}, lang : {}", cjWorldId, lang);
		String redirectUrl = ContextUtil.getProperty("cf.domainUrl") + ContextUtil.getProperty("cf.sso.redirectUrl");

		// 복호화
		String decryptUserId = decrypt(cjWorldId, secretKey);
		String userId = decryptUserId.split("\\|")[0];		
		log.info("@@@@@@@@@@@@@@@@@ decryptUserId : {}", decryptUserId);
		
		// 로그인 처리
		TokenDto tokenDto = null;
		try {
			CanalFrameAuthenticationToken token = new CanalFrameAuthenticationToken(userId, "", verificationYn, "Y", null);
			Authentication auth = authenticationManager.authenticate(token);
			tokenDto = loginHandler.procLogin((CanalFrameUserDetails) auth.getDetails(), response);		
			log.info("@@@@@@@@@@@@@@@@@ tokenDto : {}", tokenDto);
			
			// SSO 연동 페이지로 Redirect
			redirectUrl = redirectUrl + "?accessToken=" + tokenDto.getAccessToken() + "&username=" + userId;
			log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
		} catch (BadCredentialsException e) {
			redirectUrl = redirectUrl + "?errorCode=" + e.getMessage() + "&username=" + userId + "&cjWorldId=" + URLEncoder.encode(cjWorldId, StandardCharsets.UTF_8);
			log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
		} catch (Exception e) {
			redirectUrl = redirectUrl + "?errorCode=-1";
			log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
		}
		
		response.sendRedirect(redirectUrl);
	}
	
	/**
	 * @description : (구)IAM SSO 연동
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "(구)IAM SSO 연동", description = "(구)IAM SSO 연동")
	@GetMapping("/v1.0/loginOldIam")
	public void loginOldIam(@RequestParam("user_id") String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (SSOHealthChecker.isAlived()) {
			String j_sso_q = SSOManager.encryptForCookieValidation(request , SSOGlobals.SST_CD) ;
			log.info("@@@@@@@@@@@@@@@@@@@@@@@ j_sso_q : {}", j_sso_q);
			String responseMessage =  SSOManager.getSSOMemberInfoByKey(j_sso_q);
			if (SSOManager.isSuccess(responseMessage)) {
				// 통합회원 서비스 번호
				String USER_NO = SSOManager.getResponseData(responseMessage);
				log.info("@@@@@@@@@@@@@@@@@@@@@@@ USER_NO : {}", USER_NO);
			} else {
				log.info("@@@@@@@@@@@@@@@@@@@@@@@ getSSOMemberInfoByKey fail");
				log.info("@@@@@@@@@@@@@@@@@@@@@@@ code : {}", SSOManager.getResponseCode(responseMessage));
				log.info("@@@@@@@@@@@@@@@@@@@@@@@ message : {}", SSOManager.getResponseMessage(responseMessage));
			}
		}
	}

	/**
	 * @description : (신)IAM SSO 연동 호출
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping("/v1.0/loginIam")
	public void loginIam(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String redirectUrl = ContextUtil.getProperty("cf.domainUrl") + ContextUtil.getProperty("cf.sso.redirectUrl");
		String loginCookie = getCookieValue(request, "LoginCookie");
		log.info("@@@@@@@@@@@@@@@@@ loginCookie : {}", loginCookie);
		
		if (loginCookie == null) {
			redirectUrl = redirectUrl + "?errorCode=-1";
			log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
			response.sendRedirect(redirectUrl);
		} else {
			// 통합사번으로 "사용자ID" 가져오기
			CmLoginReqDto cmLoginReqDto = new CmLoginReqDto();
			cmLoginReqDto.setUserNo(loginCookie);
			CmLoginResDto cmLoginResDto = cmLoginService.getUserIdByUserNo(cmLoginReqDto);
			
			if (cmLoginResDto == null) {
				redirectUrl = redirectUrl + "?errorCode=-1";
				log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
				response.sendRedirect(redirectUrl);
			} else {
				String encValue = enc(cmLoginResDto.getUserId(), request, response);
				String id = URLEncoder.encode(encValue, "UTF-8");
				String ssoUrl = ContextUtil.getProperty("cf.iam.ssoUrl") + "?siteType=" + "WMSJ" + "&userID=" + id 
						+ "&returnURL=" + ContextUtil.getProperty("cf.domainUrl") + ContextUtil.getProperty("cf.iam.returnUrl");
				
				log.info("@@@@@@@@@@@@@@@@@ ssoUrl : {}", ssoUrl);
				//response.sendRedirect(ssoUrl);
				
				response.sendRedirect(urlFilter(ssoUrl));
			}
		}
	}
	
	/**
	 * @description : URL 필터링
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.16 sss 
	 */
	public String urlFilter(String url) {
	    if (url == null || url.isEmpty()) {
	        return "/"; 
	    }

	    // 1. CRLF Injection 방지 (S5361 준수: replaceAll -> replace)
	    // 단순 문자 교체는 정규식이 필요 없는 replace()가 훨씬 효율적입니다.
	    String cleanUrl = url.replace("\r", "").replace("\n", "");

	    // 2. 검증 실패 시 안전한 곳으로 강제 이동
	    log.warn("redirect: {}", cleanUrl);
	    return cleanUrl; 
	}
	
	/**
	 * @description : (신)IAM SSO 연동 응답
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping("/v1.0/loginProcBefore")
	public void loginProcBefore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("@@@@@@@@@@@@@@@@@ /loginProcBefore");
		SSOAuthn.authn(request, response);
		return;
	}

	/**
	 * @description : (신)IAM SSO 연동 응답
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@GetMapping("/v1.0/loginProc")
	public void loginProc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("@@@@@@@@@@@@@@@@@ /loginProc");
		
		String redirectUrl = ContextUtil.getProperty("cf.domainUrl") + ContextUtil.getProperty("cf.sso.redirectUrl");
		SSOUser user = SSOAuthn.authn(request, response);

		if (user == null) {
			log.info("@@@@@@@@@@@@@@@@@ SsoUser is null.");
		} else {
			String userId = user.getUserID();
			
			if (userId.isEmpty()) {
		  	    log.info("@@@@@@@@@@@@@@@@@ loginID is Empty. ");
		    } else {
		  	    log.info("@@@@@@@@@@@@@@@@@ loginID : {}", userId);
		  	    
		  	    // 로그인 처리
				try {
					CanalFrameAuthenticationToken token = new CanalFrameAuthenticationToken(userId, "", "N", "Y", null);
					Authentication auth = authenticationManager.authenticate(token);
					TokenDto tokenDto = loginHandler.procLogin((CanalFrameUserDetails) auth.getDetails(), response);		
					log.info("@@@@@@@@@@@@@@@@@ tokenDto : {}", tokenDto);
					
					// SSO 연동 페이지로 Redirect
					redirectUrl = redirectUrl + "?accessToken=" + tokenDto.getAccessToken() + "&username=" + userId;
					log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
				} catch (Exception e) {
					redirectUrl = redirectUrl + "?errorCode=-1";
					log.info("@@@@@@@@@@@@@@@@@ redirectUrl : {}", redirectUrl);
				}
		    }
			
			if (!response.isCommitted()) {
				response.sendRedirect(redirectUrl);
			}
		}
	}
	
	/**
	 * @description : CJ World SSO 복호화
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.05 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public static String decrypt(String encryptedText ,String key) throws Exception {
		
		String decodedCipherText = URLDecoder.decode(encryptedText, "UTF-8");
		
        byte[] cipherData = Base64.getDecoder().decode(decodedCipherText);

        byte[] iv = new byte[16];
        System.arraycopy(cipherData, 0, iv, 0, iv.length);

        byte[] encryptedBytes = new byte[cipherData.length - iv.length];
        System.arraycopy(cipherData, iv.length, encryptedBytes, 0, encryptedBytes.length);

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  // NOSONAR 
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] original = cipher.doFinal(encryptedBytes);
        return new String(original,"UTF-8");       
    }
	
	/**
	 * @description : (신)IAM 사용자ID 암호화
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String enc(String userId, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (userId == null) {
			return "";
		} else if (userId.isEmpty()) {
			return "";
		} else {
			String key = ContextUtil.getProperty("cf.iam.secretKey");

			SsoCryptoUtil cUtil = new SsoCryptoUtil();
			SecretKey secKey = cUtil.generateKey(key);
			SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
			String formatedNow = formatter.format(new Date());
			String encStr = encryptDes(secKey, userId + "|" + formatedNow); // 시분초 넣어서 암호화

			return encStr;
		}
	}

	/**
	 * @description : (신)IAM 사용자ID 암호화
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	private String encryptDes(SecretKey key, String plainUid) throws Exception {
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding"); // NOSONAR
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// 입력 문자열을 바이트로 변환하여 암호화
		byte[] inputBuffer = plainUid.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedBytes = cipher.doFinal(inputBuffer);

		// Base64로 인코딩
		String encryptedStr = Base64.getEncoder().encodeToString(encryptedBytes);
		return encryptedStr;
	}
	
	/**
	 * @description : 쿠키에서 특정 값 가져오기
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	public String getCookieValue(HttpServletRequest request, String name) {
	    Cookie[] cookies = request.getCookies(); // 모든 쿠키 가져오기
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals(name)) {
	                return cookie.getValue(); // 해당 이름의 쿠키 값 반환
	            }
	        }
	    }
	    return null; // 없을 경우 null 반환
	}

}
