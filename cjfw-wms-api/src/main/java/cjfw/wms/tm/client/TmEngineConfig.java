package cjfw.wms.tm.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "tm.engine")
@Configuration
public class TmEngineConfig {
    @Value("${tm.engine.base-url:http://49.50.129.218:8000/api/v1/optimize/base}")
    private String baseUrl;

    @Value("${tm.engine.retry-count:3}")
    private int retryCount;
    
    @Value("${tm.engine.retry-delay:2000}")
    private int retryDelay;
    
    @Value("${tm.engine.connect-timeout:30000}")
    private int connectTimeout;
    
    @Value("${tm.engine.read-timeout:120000}")
    private int readTimeout;
}
