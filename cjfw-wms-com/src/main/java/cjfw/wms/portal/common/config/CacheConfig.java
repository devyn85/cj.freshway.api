/**
 * Copyright (c) 2022 CJ OliveNetworks<br>
 * All rights reserved.<br>
 * <br>
 * This software is the proprietary information of CJ OliveNetworks<br>
 * <br>
 */
package cjfw.wms.portal.common.config;

import static java.util.stream.Collectors.toList;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();

		List<CaffeineCache> caches = Arrays.stream(CacheType.values())
				.map(cacheType -> {
					Caffeine<Object, Object> caffeine = Caffeine.newBuilder().recordStats()
							.maximumSize(cacheType.getMaximumSize())
							.expireAfterWrite(cacheType.getDuration(), cacheType.getTimeUnit());
					return new CaffeineCache(cacheType.getCacheName(), caffeine.build());
				}).collect(toList());

		cacheManager.setCaches(caches); // cache 등록
		return cacheManager;
	}
}

/**
 * CaffeineCache 개별 캐시 정의
 */
@Getter
@AllArgsConstructor
enum CacheType {

	AUTHENTICATED_MENU("authenticatedMenu", 10000, 1, TimeUnit.MINUTES)
	,USER_CODE("userCode", 10000, 1440, TimeUnit.MINUTES) // /api/cm/main/v1.0/getUserCodeList
	,TRANS_CODE("transCode", 10000, 1440, TimeUnit.MINUTES) // /api/cm/public/v1.0/getTranslationList
	// 추가 정의 샘플
	// , CACHE_SAMPLE("cacheSample", 10000, 1,TimeUnit.SECONDS)
	;

	private String cacheName; // cache명
	private long maximumSize; // 최대 사이즈
	private long duration;  // ttl
	private TimeUnit timeUnit; // 시간 단위
}