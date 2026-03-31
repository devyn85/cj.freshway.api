package cjfw.wms.comfunc.func.http.feign.client;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;

/**
 * Copyright 2022. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : wisujang
 * @date : 2023.07.04
 * @description : FeignClient Java 설정(Client 별로 설정 가능)
 * @issues :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2023.07.04        wisujang       생성
 */


public class FeignClientConfig {

    /**
     * 로그 설정
     * - FeignClient 기본 로그 설정은 NONE, 로그 확인을 위해 로그 설정 필요
     * - Feign은 DEBUG 레벨로만 로그 가능(로그 출력 시 debug로그 레벨 설정 필요)
     *  ex> logging:
     *        level:
     *          cjfw.wms.comfunc.func.http.feign.client: DEBUG
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * RequestInterceptor 설정
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        // Default Request 헤더 설정
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer sampleToken");
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }


    /**
     * Connection/Read Timeout 설정
     */
    @Bean
    public Request.Options options(){
        return new Request.Options(5000, TimeUnit.MILLISECONDS, 5000, TimeUnit.MILLISECONDS, false);
    }

}
