package cjfw.core.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cjfw.core.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : sungyeon.lee
 * @date        : 2023.10.22
 * @description : JwtUtil(Json Web Token) 기능을 구현한 Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.10.22        sungyeon.lee       생성
 */
@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    private static final String accessJwt = "ACCESS_JWT"; // NOSONAR
    private static final String algorithm = String.valueOf(SIG.HS256); // NOSONAR
    private static SecretKey key; // NOSONAR
    private static String Secret_Plain_Text; // NOSONAR
    @Value("${cf.jwt.secretPlainText}")
    private void setSecret_Plain_Text(String secretPlainText){
        Secret_Plain_Text = secretPlainText; // NOSONAR
    }
    @Value("${cf.jwt.accessExpTime:-1}")
    private long accessExpTime;
    @Value("${cf.jwt.refreshExpTime:-1}")
    private long refreshExpTime;
    private ObjectMapper objectMapper;

    /**
     * @description : JwtUtil의 생성자
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    private JwtUtil() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * @description : isValidJwt 기능을 구현한 Method
     * 아래 상태값을 통해 Token 유효성 판단
     * JWT -> 유효 : 00 / 만료 : 01 / 변조 및 그 외 : 02
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public String isValidJwt(String token) {
        return this.checkValidJwt(token);
    }

    /**
     * @description : buildAccessJwt 기능을 구현한 Method
     * JWT Access Token  생성
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public String buildAccessJwt(UserContext userContext) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String base64EncodedSecretKey = encodeBase64SecretKey(Secret_Plain_Text);
        this.key = (SecretKey) getKeyFromBase64EncodedKey(base64EncodedSecretKey); // NOSONAR 
        // header
        jwtBuilder.header().add("typ", accessJwt);
        jwtBuilder.header().add("alg", algorithm);

        Map<String, Object> paramMap = objectMapper.convertValue(userContext, Map.class);
        jwtBuilder.claims().empty().add(paramMap)
            .expiration(new Date(new Date().getTime() + accessExpTime)); // 만료일
        // signature
        jwtBuilder.signWith(key, SIG.HS256);

        return jwtBuilder.compact();
    }

    /**
     * @description : getUserContext 기능을 구현한 Method
     * 토큰 정보로 userContext 조회
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @SuppressWarnings("rawtypes")
    public UserContext getUserContext(String token) {
        UserContext userContext = null;
        String base64EncodedSecretKey = encodeBase64SecretKey(Secret_Plain_Text);
        this.key = (SecretKey) getKeyFromBase64EncodedKey(base64EncodedSecretKey); // NOSONAR
        try {
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
                .getPayload();
            userContext = objectMapper.convertValue(claims, UserContext.class);
            userContext.setValid(true);
        } catch (Exception e) {
            log.info("Invalid token, userContext return null");
        }
        return userContext;
    }

    /**
     * @description : getForceUserContext 기능을 구현한 Method
     * 토큰 정보로 userContext 조회(토큰 갱신을 위해 만료된 토큰의 Claim 정보 조회 가능)
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    public UserContext getForceUserContext(String token) {
        UserContext userContext = null;
        try {
            Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
                .getPayload();
            userContext = objectMapper.convertValue(claims, UserContext.class);
            userContext.setValid(true);
        } catch (ExpiredJwtException e) {
            Claims claims = e.getClaims();
            userContext = objectMapper.convertValue(claims, UserContext.class);
        } catch (Exception e) {
            log.info("Invalid token, userContext return null");
        }
        return userContext;
    }

    /**
     * @description : buildRefreshJwt 기능을 구현한 Method
     * JWT Refresh Token 생성
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    @SuppressWarnings("rawtypes")
    public String buildRefreshJwt() {
        JwtBuilder jwtBuilder = Jwts.builder();
        String base64EncodedSecretKey = encodeBase64SecretKey(Secret_Plain_Text);
        this.key = (SecretKey) getKeyFromBase64EncodedKey(base64EncodedSecretKey); // NOSONAR
        // header
        jwtBuilder.header().add("typ", accessJwt);
        jwtBuilder.header().add("alg", algorithm);
        jwtBuilder.claims().empty()
            .expiration(new Date(new Date().getTime() + refreshExpTime)); // 만료일

        // signature
        jwtBuilder.signWith(key, SIG.HS256);

        return jwtBuilder.compact();
    }

    /**
     * @description : checkValidJwt 기능을 구현한 Method
     * JWT Token 유효성 체크
     * @issues :
     * -----------------------------------------------------------
     * DATE              AUTHOR             MAJOR_ISSUE
     * -----------------------------------------------------------
     * 2023.10.22        sungyeon.lee       생성
     */
    private String checkValidJwt(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            // 유효
            return "00";
        } catch (ExpiredJwtException exception) {
            // 만료
            log.error("Token Expired..");
            //log.error("JwtUtil.isValidJwt().ExpiredJwtException : ", exception);
            return "01";
        } catch (JwtException jwtException) {
            // 변조
            log.error("Token JwtException..");
            //log.error("JwtUtil.isValidJwt().JwtException : ", exception);
//            throw jwtException;
            return "02";
        } catch (Exception e) {
            // 그 외
            log.error("Token Exception..");
            //log.error("JwtUtil.isValidJwt().Exception : ", e);
//            throw e;
            return "02";
        }
    }
}
