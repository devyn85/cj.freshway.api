package cjfw.wms.portal.common.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Copyright 2024. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : th.jeon
 * @date : 2024.01.25
 * @description : 비동기로 수행될 수 있도록 설정을 정의한 Config Class
 * @issues :
 * -----------------------------------------------------------
 * DATE                 AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2024.01.25        th.jeon       생성
 **/
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(90);
        executor.setMaxPoolSize(1000);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("FullResExecutor-");
        executor.initialize();
        return executor;
    }
}
