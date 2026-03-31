/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cjfw.auth.common.CustomAuthenticationEntryPoint;
import cjfw.auth.common.service.CJSecurityRulesService;
import cjfw.auth.jwt.JwtAuthUtil;
import cjfw.auth.jwt.JwtAuthenticationFilter;
import cjfw.core.redis.RedisService;
import cjfw.core.utility.JwtUtil;
import cjfw.core.web.filter.EnvironmentSetupFilter;
import cjfw.wms.cm.CanalFrameAuthenticationProvider;
import cjfw.wms.cm.CanalFrameLoginHandler;
import cjfw.wms.cm.CanalFrameLogoutHandler;
import cjfw.wms.cm.service.CmUserService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final EnvironmentSetupFilter environmentSetupFilter;

    private final CanalFrameAuthenticationProvider canalFrameAuthenticationProvider;

    private final CJSecurityRulesService cjSecurityRulesService;
    
    private final CmUserService cmUserService;

    private final JwtUtil jwtUtil;
    
    private final JwtAuthUtil jwtAuthUtil;
    
    private final RedisService redisService;

    @Value("${cf.auth.pattern}")
    private String[] authPattern;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] secAuthPattern = authPattern;

        /**
         *  HTTP 요청에 대한 권한 부여 규칙을 설정
         */
        // secAuthPattern 패턴에 매칭되는 요청은 인증 제외
        // 그 외의 모든 요청은 모든 사용자에게 허용
        http.authorizeHttpRequests((authz) -> authz
            .requestMatchers(secAuthPattern).permitAll()
            .anyRequest().authenticated());
        // HTTP 기본 인증을 비활성화, REST API 이므로 기본 로그인 폼 리다이렉션을 사용하지 않음
        http.httpBasic(httpBasicCustomizer -> httpBasicCustomizer.disable());
        // Cross-Site Request Forgery (CSRF) 보안을 비활성화, REST API 이므로 CSRF 보안이 불필요
        http.csrf(csrfCustomizer -> csrfCustomizer.disable());
        // Cross-Origin Resource Sharing (CORS)를 기본 설정으로 구성
        http.cors(Customizer.withDefaults()); //= corsCustomizer->{}

        /* 보안 헤더 구성 */
        // 프레임에 대해 같은 출처만 허용하도록 설정
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        // HTTP Strict Transport Security (HSTS)는 비활성화
        http.headers(headers -> headers.httpStrictTransportSecurity(hstsCustomizer -> hstsCustomizer.disable()));
        // 세션을 사용하지 않고 토큰을 통해 상태를 관리
        http.sessionManagement((sessionManagement) ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 사용자 정의 필터를 필터 체인에 추가
        http.addFilterBefore(environmentSetupFilter, ChannelProcessingFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(secAuthPattern, jwtAuthUtil),
            UsernamePasswordAuthenticationFilter.class);

        // 예외 처리 구성
        http.exceptionHandling(authenticationManager -> authenticationManager
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        // 사용자 정의 인증 프로바이더 (canalFrameAuthenticationProvider) 등록
        http.authenticationProvider(canalFrameAuthenticationProvider);

        return http.build();
    }


    @Bean
    public CanalFrameLoginHandler loginHandler(){
        return new CanalFrameLoginHandler(cmUserService, jwtUtil, redisService);
    }

    @Bean
    public CanalFrameLogoutHandler logoutHandler(){
        return new CanalFrameLogoutHandler(cjSecurityRulesService, cmUserService);
    }

}

