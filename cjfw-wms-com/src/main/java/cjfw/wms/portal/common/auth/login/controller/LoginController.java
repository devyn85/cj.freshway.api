package cjfw.wms.portal.common.auth.login.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.auth.jwt.JwtAuthUtil;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.exception.UnAuthorizedException;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.ApiResult;
import cjfw.core.utility.DateUtil;
import cjfw.core.utility.SsoCryptoUtil;
import cjfw.wms.cm.CanalFrameLoginHandler;
import cjfw.wms.cm.CanalFrameLoginValidator;
import cjfw.wms.cm.CanalFrameLogoutHandler;
import cjfw.wms.portal.common.auth.login.dto.FindIdGetReqDto;
import cjfw.wms.portal.common.auth.login.dto.FindIdGetResDto;
import cjfw.wms.portal.common.auth.login.dto.LoginDto;
import cjfw.wms.portal.common.auth.login.dto.PwdChangeComResDto;
import cjfw.wms.portal.common.auth.login.dto.PwdChangeReqDto;
import cjfw.wms.portal.common.auth.login.dto.SsoDto;
import cjfw.wms.portal.common.auth.login.dto.TokenDto;
import cjfw.wms.portal.common.auth.login.dto.VrfctnCdCheckReqDto;
import cjfw.wms.portal.common.auth.login.dto.VrfctnCdSendReqDto;
import cjfw.wms.portal.common.auth.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "로그인", description = "로그인 및 토큰 처리 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/common/auth")
public class LoginController {

    private final CanalFrameLoginHandler loginHandler;

    private final LoginService loginService;

    private final CanalFrameLogoutHandler logoutHandler;

    private final AuthenticationManager authenticationManager;

    private final JwtAuthUtil jwtAuthUtil;

    private final CJSecurityRulesService cJSecurityRulesService;

    @Operation(summary = "로그인", description = "로그인을 처리하고 토큰을 발행한다")
    @PostMapping("/login")
    public ApiResult<TokenDto> login(@RequestBody LoginDto loginDto, HttpServletResponse res) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword(), null);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            TokenDto tokenDto = loginHandler.procLogin((CanalFrameUserDetails) auth.getDetails(), res);
            return ApiResult.createResult(tokenDto);
        } catch (AuthenticationException e) {
            log.warn("Login Failed!! ");
            return loginHandler.procLoginFail(e);
        }
    }

    @Operation(summary = "로그아웃", description = "로그아웃 정보를 기록하고, RefreshToken을 삭제한다")
    @GetMapping("/logout")
    public ApiResult logout(HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.procLogout(request, response);
        return ApiResult.createResult();
    }

    @Operation(summary = "AccessToken 갱신", description = "AccessToken을 갱신 후 리턴한다")
    @PostMapping("/refreshtoken")
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

    @Operation(summary = "아이디찾기", description = "사용자 아이디를 찾는다")
    @PostMapping("/findIdSearch")
    public ApiResult<List<FindIdGetResDto>> findIdSearch(@RequestBody @Valid FindIdGetReqDto findIdGetReqDto) {
        return ApiResult.createResult(loginService.getFindId(findIdGetReqDto));
    }

    @Operation(summary = "인증코드 전송", description = "비밀번호 변경 시 인증코드 전송")
    @PostMapping(value = "/sendVerificationCode")
    public ApiResult<PwdChangeComResDto> sendVerificationCode(@RequestBody @Valid VrfctnCdSendReqDto vrfctnCdSendReqDto) {
        try {
            PwdChangeComResDto pwdChangeComResDto = loginService.sendVerificationCode(vrfctnCdSendReqDto);
            return ApiResult.createResult(pwdChangeComResDto, "MSG.COM.SUC.025");
        }catch(UserHandleException e){
            // log.info("{} / {}", e.getErrorCode(), e.getErrorMessage());
            return ApiResult.createResult(PwdChangeComResDto.builder().successYn("0").build(), e.getErrorMessage());
        }
    }

    @Operation(summary = "인증코드 체크", description = "비밀변호 변경 시 발급된 인증코드 체크")
    @PostMapping(value = "/checkVerificationCode")
    public ApiResult<PwdChangeComResDto> checkVerificationCode(@RequestBody @Valid VrfctnCdCheckReqDto vrfctnCdCheckReqDto) {
        try {
            String resultMsgCode = loginService.checkVerificationCode(vrfctnCdCheckReqDto);
            return ApiResult.createResult(PwdChangeComResDto.builder().successYn("1").build(), resultMsgCode);
        }catch(UserHandleException e){
            // log.info("{} / {}", e.getErrorCode(), e.getErrorMessage());
            return ApiResult.createResult(PwdChangeComResDto.builder().successYn("0").build(), e.getErrorMessage());
        }
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경 실행")
    @PostMapping(value = "/findPwdSearch")
    public ApiResult<PwdChangeComResDto> savePwdChange(@RequestBody @Valid PwdChangeReqDto pwdChangeReqDto) {
        try {
            String resultMsgCode = loginService.savePwdChange(pwdChangeReqDto);
            return ApiResult.createResult(PwdChangeComResDto.builder().successYn("1").build(), resultMsgCode);
        }catch(UserHandleException e){
            // log.info("{} / {}", e.getErrorCode(), e.getErrorMessage());
            return ApiResult.createResult(PwdChangeComResDto.builder().successYn("0").build(), e.getErrorMessage());
        }
    }


    @Operation(summary = "SSO", description = "SSO기반 로그인 처리")
    @PostMapping(value = "/sso")
    public ApiResult<TokenDto> sso(@RequestBody @Valid SsoDto ssoDto, HttpServletRequest request, HttpServletResponse res) {

        String decryptUserId = "";
        if(ssoDto.getEnc() != null && ssoDto.getEnc().equals("N")) {
            decryptUserId = ssoDto.getCjWorldId();
        } else {
            try {
                SsoCryptoUtil td = new SsoCryptoUtil();
                decryptUserId = td.decrypt(ssoDto.getCjWorldId());
            } catch (Exception e) {
                return loginHandler.procLoginFail(new BadCredentialsException("010"));
            }
        }
        log.info("decryptUserId: {}", decryptUserId);

        try {
            Map<String, String> inParams = new HashMap<>();
            inParams.put("userId", decryptUserId);
            Map<String, Object> user = cJSecurityRulesService.getUser(inParams);
            if (user == null) { // 사용자 확인
                log.info("[{}] login fail!!", decryptUserId);
                throw new BadCredentialsException("010");
            }
            if("02".equals(user.get("USER_STATUS"))){ // 계정잠김 확인
                log.info("[{}] login fail!!", decryptUserId);
                throw new BadCredentialsException("080");
            }

            // 네트워크 접근 제어 체크
            String remoteNetworkAddress = (String)request.getAttribute(CanalFrameConstants.CLIENT_IP);
            List<Object> allowedIp = cJSecurityRulesService.getUserAllowedIpList(inParams);
            Map<String, String> validResultMap = new HashMap();
//            if(!CanalFrameLoginValidator.validLogin(validResultMap, remoteNetworkAddress, user, allowedIp)){
//                log.info("[{}] login fail!!", decryptUserId);
//                throw new BadCredentialsException(validResultMap.get("result"));
//            }

            // 로그인 성공 로그 기록
            String loginDateTime = DateUtil.getDateTimeToString(LocalDateTime.now());
    		
    	
            Map<String, String> loginMap = new HashMap<String, String>();
            loginMap.put("userId", decryptUserId);
            loginMap.put("LOG_DTM", loginDateTime);
            loginMap.put("IP_ADDR", remoteNetworkAddress);
            cJSecurityRulesService.insertLoginNewTx(loginMap);
            loginMap.put("SUCCESS_YN", "1");
            cJSecurityRulesService.updateSecurityRules(loginMap);

            // 권한정보 설정
            Map<String, String> userIdMap = new HashMap<String, String>();
            userIdMap.put("USER_ID", decryptUserId);
            List<Object> authList = cJSecurityRulesService.getUserRolesList(userIdMap);
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            for(int inx = 0 ; inx < authList.size() ; inx ++) {
                Map<String, String> auth = (Map<String, String>) authList.get(inx);
                roles.add(new SimpleGrantedAuthority(auth.get("AUTHORITY")));
            }
            CanalFrameUserDetails customUserDetails = new CanalFrameUserDetails(decryptUserId, null);
            customUserDetails.setRoles(roles);

            // 로그인 처리
            TokenDto tokenDto = loginHandler.procLogin(customUserDetails, res);
            return ApiResult.createResult(tokenDto);
        }catch(AuthenticationException e) {
            log.info("[{}] login fail!!", decryptUserId);
            return loginHandler.procLoginFail(e);
        }
    }

}
