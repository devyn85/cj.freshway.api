package cjfw.core.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
