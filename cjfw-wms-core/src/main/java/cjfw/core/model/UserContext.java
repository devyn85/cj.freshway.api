package cjfw.core.model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.20
 * @description : UserContext 기능을 구현한 Class
 * 				  Request 별 사용자 Context 정보(Stateless에 대한 전략을 엄격한 수준으로 수립 할 경우에는 우회 방안 고려)
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.20        sungyeon.lee       생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class UserContext {
	/**사용자ID*/
    private String userId;
    /**사용자명*/
    private String userNm;
    /**센터코드*/
    private String dccode;
    /**고객코드*/
    private String storerkey;
    /**조직코드*/
    private String organize;
    /**창고코드*/
    private String area;
    /**접속DB SPID*/
    private String spid;
    /**다국어코드*/
    private String lang;
    /** 권한코드 */
    private String authority;
    /**Roll*/
    private String roles;
    /**통합회원번호*/
    private String userNo;
    /**메일ID*/
    private String emailAddr;
    /** 소속구분 */
    private String emptype;
    /** 사원번호 */
    private String empNo;
    /** 업체코드 */
    private String custkey;
    /** 대표사용자ID여부 */
    private String repUserIdYn;
    
    // JWT 토큰화 제외 필드들
    /**Locale*/
    @JsonIgnore
    private Locale locale;
    @JsonIgnore
    private String clientIp;
    /**Token 유효여부*/
    @JsonIgnore
    private boolean isValid;
    /**Token만료여부*/
    @JsonIgnore
    private boolean isTokenExpired;

    /**
     * 
     * @description : getLocaleString 기능을 구현한 Method
     * 				  Locale String 조회(ex. ko_kr)
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    @JsonIgnore
    public String getLocaleString(){
        if(this.locale == null){
            return null;
        }
        return this.locale.toString().toLowerCase();

    }

    /**
     * 
     * @description : getRoleList 기능을 구현한 Method
     * @issues      :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.20        sungyeon.lee       생성
     */
    @JsonIgnore
    public List<String> getRoleList(){
        if(this.roles == null){
            return null;
        }
        return Arrays.asList(this.roles.split("\\|"));
    }

    /**
     * 이하 Bean scope 테스트 용
     */
//    @JsonIgnore
//    private String uuid;
//    
//    @PostConstruct
//    public void init() {
//        uuid=UUID.randomUUID().toString();
//        System.out.println(">>>>>> request scope bean create uuid = " + uuid);
//    }
//    
//    @PreDestroy
//    public void close() {
//        System.out.println(">>>>>> request scope bean destroy uuid = " + uuid);
//    }
}
