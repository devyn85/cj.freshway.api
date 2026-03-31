package cjfw.wms.portal.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cjfw.auth.ext.ExternalAuthFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ExternalAuthFilter> register(ExternalAuthFilter filter) {
        FilterRegistrationBean<ExternalAuthFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(filter);
        reg.addUrlPatterns("/api/cm/public/outprocess/v1.0/saveExdcDailyOnhandQty999999"); // 외부창고 API 재고현황 인터페이스
        reg.setOrder(1);
        return reg;
    }
}
