package cjfw.wms.cm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cjfw.core.exception.UserHandleException;
import cjfw.core.utility.ContextUtil;
import cjfw.wms.cm.dto.CmMainUserResDto;
import cjfw.wms.portal.common.auth.login.dto.PwdChangeReqDto;
import cjfw.wms.portal.common.auth.login.entity.PwdHstEntity;

public class CanalFrameLoginValidator {

    private CanalFrameLoginValidator() {
    }

    private static final Logger log = LoggerFactory.getLogger(CanalFrameLoginValidator.class);
    private static final String RESULT = "result";
//    private static final String USERID = "USER_ID";
//    private static final String	NEWPASSWORD = "PWD";
//    private static final String NEWPASSWORDCHK = "PWDCHECK";

    public static boolean validLogin(Map validResultMap, String remoteNetworkAddress, CmMainUserResDto user, List<Object> allowedIp, String verificationYn){
        boolean isValid = true;

        //네트워크 접근 통제
        isValid = validNetworkAccessControl(validResultMap, remoteNetworkAddress, user, allowedIp);
        if(!isValid){
            return isValid;
        }

        //비밀번호 검사
        //비밀번호 불일치 카운트
        int passwordFailCount = Integer.parseInt(StringUtils.defaultIfEmpty(String.valueOf(user.getFailInCnt()), "0"));
        //비밀번호 오류 회수 초과
        if(passwordFailCount>5){
            validResultMap.put(RESULT, "050");
            isValid = false;
            return isValid;
        }
        
        
        int passwordDiffCount = Integer.parseInt(StringUtils.defaultIfEmpty(String.valueOf(user.getPwdRevByDiff()), "0"));
        int useDiffCount = Integer.parseInt(StringUtils.defaultIfEmpty(String.valueOf(user.getUseRevByDiff()), "0"));
        if (0 < passwordDiffCount || 0 < useDiffCount) {
        	validResultMap.put(RESULT, "080"); // 잠긴 계정입니다.
            isValid = false;
            return isValid;
        }
        
        // 비밀번호 초기화 대상 검사
        if("1".equals(user.getTmpPwdYn())){
            validResultMap.put(RESULT, "060");
            isValid = false;
            return isValid;
        }
        
        // 비밀번호 사용기간 만료검사 (만료되기 7일전 부터 안내)
        // 비밀번호 변경 안 할 경우 검증 안하게 "verificationYn" 정보 추가
        if (!"N".equals(verificationYn)) {
            if(-8 < passwordDiffCount && passwordDiffCount < 1){
                validResultMap.put(RESULT, "040");
                isValid = false;
                return isValid;
            }
        }
        
        //다중로그인 검사
        //기준:동일 로그인/로그아웃 기록중 로그아웃 기록이 없는 다른 IP가 있는 경우
        //IP_ADDR, LOG_DTM->LOG_DT
        //validResultMap.put(RESULT, "110");
        return isValid;
    }
    /**
     * 보안요구사항기술서<br>
     * 구분 			|요구사항ID	|요구사항명			|상세설명<br>
     * 접근 및 사용 통제	|AOS-008	|네트워크 접근 통제	|그룹 내부 IP로 시스템 접근을 통제함,외부 IP의 경우 계정 소유자 별로 IP 접근 통제를 적용<br>
     * application.properties
     * accessIpRange
     * TB_CF_ALLOWED_IP
     * @param ip
     * @return true or false
     */
    private static boolean validNetworkAccessControl(Map validResultMap, String ip, CmMainUserResDto user, List<Object> allowedIp){
        boolean isValid = true;
        String springProfiles = System.getProperty("spring.profiles.active", "local");
        //로컬환경인 경우 통과
        if("local".equals(springProfiles)){
            return isValid;
        }else{
            String allowAllIpYn = StringUtils.defaultIfEmpty((String)user.getAllowAllIpYn(),"0");
            //모든 아이피 허용인 경우(정책에 없는 부분)
            if("1".equals(allowAllIpYn)) {
                return isValid;
            }else{
                //그룹 내부 IP로 시스템 접근을 통제
                isValid = validRangeIp(ip);
                if(!isValid){
                    isValid = validAllowedIp(ip, allowedIp);
                    if(!isValid){
                        validResultMap.put(RESULT, "100");
                    }
                }
            }
        }
        return isValid;
    }

    @SuppressWarnings("unchecked")
    private static boolean validAllowedIp(String ip, List<Object> allowedIp){
        boolean isValid = false;
        for(Object obj : allowedIp){
            Map<String, String> externalIp = (Map<String, String>)obj;
            if(ip.equals(externalIp.get("IP_ADDR"))){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private static boolean validRangeIp(String ip){
        boolean isValid = true;
        //그룹 내부 IP로 시스템 접근을 통제
        String[] accessIpRangeList = ContextUtil.getProperty("cf.accessIpRange").split(",");
        try {
            for(String ipRange : accessIpRangeList){
                String[] temp = ipRange.split("~");
                if(temp.length>1){
                    InetAddress minInetAddress = InetAddress.getByName(temp[0]);
                    InetAddress maxInetAddress = InetAddress.getByName(temp[1]);
                    InetAddress currentInetAddress = InetAddress.getByName(ip);
                    isValid = minInetAddress.hashCode() < currentInetAddress.hashCode() && maxInetAddress.hashCode() > currentInetAddress.hashCode();
                    if(isValid) break;
                }
            }
        } catch (UnknownHostException e) {
            log.info("UnknownHostException",e);
        }
        return isValid;
    }
    @SuppressWarnings("unchecked")
    public static boolean validPassword(PwdChangeReqDto pwdChangeReqDto, List<PwdHstEntity> passwordHistory){
        String newPassword = pwdChangeReqDto.getPwd();
        String newPasswordChk = pwdChangeReqDto.getPwdCheck();
        String userId = pwdChangeReqDto.getUserId();

        if(!newPassword.equals(newPasswordChk)) {
            //비밀번호와 비밀번호확인이 일치하지 않습니다.
            throw new UserHandleException("MSG.COM.ERR.076");
        }
        //공백 문자 체크
        //공백 문자는 사용할 수 없습니다.
        if(newPassword.contains(" ")) {
            throw new UserHandleException("MSG.COM.ERR.031");
        }
        //8자 이상  체크
        //비밀번호는 8자리 이상이어야 합니다.
        if(newPassword.length() < 8) {
            throw new UserHandleException("MSG.COM.ERR.032");
        }
        int pwComplexCnt = 0;
        //숫자 포함
        if(Pattern.compile("[0-9]+").matcher(newPassword).find()) {
            pwComplexCnt++;
        }
        //영문 포함
        if(Pattern.compile("[a-zA-Z]+").matcher(newPassword).find()) {
            pwComplexCnt++;
        }
        //특수문자 포함
        if(Pattern.compile("[!-/:-@\\[-`\\{-~]+").matcher(newPassword).find()) {
            pwComplexCnt++;
        }
        //영문,숫자,특수 문자 가 2개 이상 있는지 확인
        //비밀번호는 영문, 숫자, 특수문자 중 2종 이상 혼용하여야 합니다.
        if(pwComplexCnt < 2) {
            throw new UserHandleException("MSG.COM.ERR.033");
        }
        //비밀번호에는 영문, 숫자, 특수문자 외의 문자를 사용할 수 없습니다.
        if(Pattern.compile("[^!-~]+").matcher(newPassword).find()) {
            throw new UserHandleException("MSG.COM.ERR.034");
        }
        //ID와 4자리 이상 동일한 비밀번호는 사용할 수 없습니다.
        for(int i = 0; i < newPassword.length() - 4; i++) {
            if((userId.indexOf(newPassword.substring(i, i + 4))) > -1) {
                throw new UserHandleException("MSG.COM.ERR.035");
            }
        }
        //비밀번호에는 4자리 이상 반복되는 문자나 숫자를 사용할 수 없습니다.
        if(Pattern.compile("w*([!-~])\\1\\1\\1").matcher(newPassword).find()) {
            throw new UserHandleException("MSG.COM.ERR.036");
        }
        //비밀번호에는 4자리 이상 연속된 문자나 숫자를 사용할 수 없습니다.
        int serialCount = 1;
        for(int i = 1; i < newPassword.length(); i++) {
            if(newPassword.charAt(i - 1) >= '0' && newPassword.charAt(i - 1) <= '9'
                    || (newPassword.charAt(i) >= 'A' && newPassword.charAt(i) <= 'Z')
                    || (newPassword.charAt(i) >= 'a' && newPassword.charAt(i) <= 'z')) {
                if(newPassword.charAt(i - 1) + 1 == newPassword.charAt(i)) {
                    serialCount++;
                } else {
                    serialCount = 1;
                }
            } else {
                serialCount = 1;
            }
            if(serialCount > 3) {
                throw new UserHandleException("MSG.COM.ERR.037");
            }
        }

        LocalDate today = LocalDate.now();
        LocalDate prechangedate;
        for(PwdHstEntity userData : passwordHistory){
//            if(newPassword.equals(CryptoUtil.SHA256(userData.get("USER_PWD")))){
//                // 이전 패스워드와 중복
//                outParams.setVariable(RESULT, "020");
//                return false;
//            }
            prechangedate = Instant.ofEpochMilli(userData.getPwdChgDt().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if(prechangedate.isEqual(today)){
                // 1일 1회 변경 제한
                throw new UserHandleException("MSG.COM.ERR.091", new String[]{"1"});
            }
        }
        return true;
    }
}
