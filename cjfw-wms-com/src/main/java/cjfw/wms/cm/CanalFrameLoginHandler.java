package cjfw.wms.cm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.AuthenticationException;

import cjfw.auth.common.model.CanalFrameUserDetails;
import cjfw.auth.jwt.JwtAuthUtil;
import cjfw.core.common.CanalFrameConstants;
import cjfw.core.model.ApiResult;
import cjfw.core.model.UserContext;
import cjfw.core.redis.RedisService;
import cjfw.core.utility.JwtUtil;
import cjfw.wms.cm.dto.CmMainUserReqDto;
import cjfw.wms.cm.dto.CmMainUserResDto;
import cjfw.wms.cm.service.CmUserService;
import cjfw.wms.portal.common.auth.login.dto.TokenDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CanalFrameLoginHandler {

    private final CmUserService cmUserService;

    private final JwtUtil jwtUtil;
    
    private final RedisService redisService;

    public TokenDto procLogin(CanalFrameUserDetails userDetails, HttpServletResponse res) {

        List<String> roleList = new ArrayList<String>();
        if(userDetails.getRoles().size()>0){
            for(int i=0;i<userDetails.getRoles().size();i++){
                roleList.add(userDetails.getRoles().get(i).getAuthority());
            }
        }

        Map<String, String> userIdMap = new HashMap<String, String>();
        userIdMap.put("userId",userDetails.getUsername());
        CmMainUserReqDto cmMainUserReqDto = new CmMainUserReqDto();
        cmMainUserReqDto.setGUserId(userDetails.getUsername());

        CmMainUserResDto user = cmUserService.getCmUser(cmMainUserReqDto);

        // userMap 설정(토큰에 포함시킬 정보 설정)
        UserContext userContext = new UserContext();
        userContext.setUserId((String) Optional.ofNullable(user.getUserId()).orElse(""));
        userContext.setUserNm((String) Optional.ofNullable(user.getUserNm()).orElse(""));
        userContext.setDccode((String) Optional.ofNullable(user.getDefDccode()).orElse(""));
        userContext.setStorerkey((String) Optional.ofNullable(user.getDefStorerkey()).orElse(""));
        userContext.setOrganize((String) Optional.ofNullable(user.getDefOrganize()).orElse(""));
        userContext.setArea((String) Optional.ofNullable(user.getDefArea()).orElse(""));
        userContext.setSpid((String) Optional.ofNullable(user.getSpid()).orElse(""));
        userContext.setLang((String) Optional.ofNullable(user.getLangCode()).orElse(""));
        userContext.setAuthority((String) Optional.ofNullable(user.getAuthority()).orElse(""));
        userContext.setRoles(String.join("|", roleList));
        userContext.setUserNo(Optional.ofNullable(user.getUserNo()).orElse(""));
        userContext.setEmailAddr(user.getEmailAddr());
        userContext.setEmptype(user.getEmptype());
        userContext.setEmpNo(user.getEmpNo());
        userContext.setCustkey(user.getCustkey());
        userContext.setRepUserIdYn(user.getRepUserIdYn());
        // 필요한 정보 추가

        // RefreshToken 생성
        String refreshToken = jwtUtil.buildRefreshJwt();
        JwtAuthUtil.setRefreshTokenInHttpOnlyCookie(res, refreshToken, "refresh_token");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", userContext.getUserId());
        paramMap.put("refreshToken", refreshToken);
        cmUserService.updateUserRefreshToken(paramMap);
        
        // AccessToken 생성
        String accessToken = jwtUtil.buildAccessJwt(userContext);
        
        // Redis에 사용자 정보 저장
        // 중복 로그인 체크 DB 부화 줄이기 위해 Redis 사용
//        String profile = System.getProperty("spring.profiles.active", "local");
//        redisService.save(profile+":user:spid:"+userContext.getUserId(), Optional.ofNullable(user.getSpid()).orElse(""));

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public ApiResult procLoginFail(AuthenticationException exception){
        int status = CanalFrameConstants.ERROR_CODE_SUCCESS;
        String message = "";
        if(exception.getMessage().equals("010")){
            message = "로그인 정보가 잘못되었습니다.";
            status = 10;
        } else if(exception.getMessage().equals("020")){
            message = "최근 로그인 후 90일이 지난 사용자입니다.";
            status = 20;
        } else if(exception.getMessage().equals("030")){
            message = "최초 로그인 사용자는 비밀번호 변경이 필요합니다.";
            status = 30;
        } else if(exception.getMessage().equals("040")){
            message = "비밀번호 사용기간 만료되었습니다.";
            status = 40;
        } else if(exception.getMessage().equals("050")){
            message = "비밀번호 오류 횟수 초과되었습니다.";
            status = 50;
        } else if(exception.getMessage().equals("060")){
            message = "임시 비밀번호 발급 상태입니다.";
            status = 60;
        } else if(exception.getMessage().equals("070")){
            message = "비밀번호 오류 횟수 10회에 도달하여 해당 계정을 잠금 처리합니다.";
            status = 70;
        } else if(exception.getMessage().equals("080")){
            message = "잠긴 계정입니다.";
            status = 80;
        } else if(exception.getMessage().equals("090")){
            message = "SSO 처리 비대상자입니다.";
            status = 90;
        } else if(exception.getMessage().equals("100")){
            message = "사용가능한 IP가 아닙니다.";
            status = 100;
        }
        return ApiResult.createResult(message, status);
    }

}
