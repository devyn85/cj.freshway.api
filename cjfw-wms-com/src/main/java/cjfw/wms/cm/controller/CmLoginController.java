package cjfw.wms.cm.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.auth.jwt.JwtAuthUtil;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.exception.UnAuthorizedException;
import cjfw.core.model.ApiResult;
import cjfw.wms.cm.CanalFrameAuthenticationToken;
import cjfw.wms.cm.CanalFrameLoginHandler;
import cjfw.wms.cm.CanalFrameLogoutHandler;
import cjfw.wms.cm.dto.CmLoginReqDto;
import cjfw.wms.cm.dto.CmLoginResDto;
import cjfw.wms.cm.service.CmLoginService;
import cjfw.wms.portal.common.auth.login.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JangGwangSeok (breaker3317@cj.net) 
 * @date : 2025.05.09 
 * @description : 로그인 API Controller
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.09 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
 */
@Tag(name = "Login", description = "로그인 API")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/cm/login")
public class CmLoginController {
	
	private final CanalFrameLoginHandler loginHandler;
	
	private final AuthenticationManager authenticationManager;
	
	private final CmLoginService cmLoginService;
	
	private final CanalFrameLogoutHandler logoutHandler;	
	
	private final JwtAuthUtil jwtAuthUtil;

	/**
	 * @description : 로그인을 처리하고 토큰 발행
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.09 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "로그인", description = "로그인을 처리하고 토큰을 발행한다")
    @PostMapping("/v1.0/login")
    public ApiResult<TokenDto> login(@RequestBody CmLoginReqDto cmLoginReqDto, HttpServletResponse res) {
		CanalFrameAuthenticationToken token = new CanalFrameAuthenticationToken(cmLoginReqDto.getUsername(), cmLoginReqDto.getPassword(), cmLoginReqDto.getVerificationYn(), "N", null);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            TokenDto tokenDto = loginHandler.procLogin((CanalFrameUserDetails) auth.getDetails(), res);
            return ApiResult.createResult(tokenDto);
        } catch (AuthenticationException e) {
            log.warn("Login Failed!! ");
            return loginHandler.procLoginFail(e);
        }
    }
	
	/**
	 * @description : 인증번호 전송
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "인증번호 전송", description = "인증번호 전송")
	@PostMapping("/v1.0/sendVerificationCode")
    public ApiResult<String> saveVerificationCode(@RequestBody CmLoginReqDto cmLoginReqDto) {
		return ApiResult.createResult(cmLoginService.saveVerificationCode(cmLoginReqDto));
    }
	
	/**
	 * @description : 인증번호 확인
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "인증번호 확인", description = "인증번호 확인")
    @GetMapping("/v1.0/checkVerificationCode")
    public ApiResult<String> checkVerificationCode(CmLoginReqDto cmLoginReqDto) {
		return ApiResult.createResult(cmLoginService.checkVerificationCode(cmLoginReqDto));
    }
	
	/**
	 * @description : 사용자ID 중복 count 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.01 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자ID 중복 count 조회", description = "사용자ID 중복 count 조회")
    @GetMapping("/v1.0/getDupUserIdCnt")
    public ApiResult<CmLoginResDto> getDuplicateUserIdCount(CmLoginReqDto cmLoginReqDto) {
		return ApiResult.createResult(cmLoginService.getDuplicateUserIdCount(cmLoginReqDto));
    }
	
	/**
	 * @description : 사용자 정보 수정 
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 정보 수정", description = "사용자 정보 수정")
	@PostMapping("/v1.0/saveUserInfo")
    public ApiResult<String> saveUserInfo(@RequestBody CmLoginReqDto cmLoginReqDto) {
		return ApiResult.createResult(cmLoginService.saveUserInfo(cmLoginReqDto));
    }
	
	/**
	 * @description : 사용자 비밀번호 수정
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.14 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "사용자 비밀번호 수정", description = "사용자 비밀번호 수정")
	@PostMapping("/v1.0/saveUserPwdNo")
    public ApiResult<String> saveUserPwdNo(@RequestBody CmLoginReqDto cmLoginReqDto) {
		return ApiResult.createResult(cmLoginService.saveUserPwdNo(cmLoginReqDto));
    }
	
	/**
	 * @description : 로그아웃
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "로그아웃", description = "로그아웃 정보를 기록하고, RefreshToken을 삭제한다")
    @GetMapping("/v1.0/logout")
    public ApiResult<String> logout(HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.procLogout(request, response);
        return ApiResult.createResult();
    }
	
	/**
	 * @description : AccessToken 갱신
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.03 JangGwangSeok (breaker3317@cj.net) 생성 </pre>
	 */
	@Operation(summary = "AccessToken 갱신", description = "AccessToken을 갱신 후 리턴한다")
    @PostMapping("/v1.0/refreshtoken")
    public ApiResult<TokenDto> refreshToken(@RequestBody TokenDto tokenDto,HttpServletResponse res, @CookieValue(value = "refresh_token", defaultValue = "null") String refreshToken) {
        String newAccessToken = jwtAuthUtil.refreshAccessToken(refreshToken, tokenDto.getAccessToken());
        if(newAccessToken != null){
            TokenDto newTokenDto = TokenDto.builder().accessToken(newAccessToken).build();
            String newRefreshToken = jwtAuthUtil.checkAndUpdateRefreshToken(tokenDto.getRefreshToken(), newAccessToken); // RefreshToken 갱신
            if(newRefreshToken != null){
                JwtAuthUtil.setRefreshTokenInHttpOnlyCookie(res, newRefreshToken, "refresh_token");
            }
            return ApiResult.createResult(newTokenDto);
        } else{
            throw new UnAuthorizedException(CanalFrameConstants.ERROR_CODE_REFRESHTOKEN_NOTVALID, "MSG.COM.ERR.102");
        }
    }

}
