package com.fayardev.regms.postservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String KEY_PREFIX = "product:";

    public void saveProduct(String productId, String productData) {
        redisTemplate.opsForValue().set(KEY_PREFIX + productId, productData);
    }

    public String getProduct(String productId) {
        return redisTemplate.opsForValue().get(KEY_PREFIX + productId);
    }

    public void deleteProduct(String productId) {
        redisTemplate.delete(KEY_PREFIX + productId);
    }
}
