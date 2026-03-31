package cjfw.core.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		// RedisTemplate 객체 생성 - String 타입의 키와 Object 타입의 값을 저장할 수 있도록 설정
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // RedisConnectionFactory 설정
        template.setConnectionFactory(connectionFactory);

        // JSON 직렬화/역직렬화를 위한 직렬화기 생성 - 자바 객체를 JSON으로 변환
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        template.setKeySerializer(new StringRedisSerializer());      // 키 직렬화 설정
        template.setValueSerializer(serializer);                     // 값 직렬화 설정 - 객체를 JSON으로 직렬화
        template.setHashKeySerializer(new StringRedisSerializer());  // 해시 자료구조의 키 직렬화 설정
        template.setHashValueSerializer(serializer);                 // 해시 자료구조의 값 직렬화 설정 - 객체를 JSON으로 직렬화

        template.afterPropertiesSet();   // 설정 후 초기화 작업 수행
        return template;
    }
}
