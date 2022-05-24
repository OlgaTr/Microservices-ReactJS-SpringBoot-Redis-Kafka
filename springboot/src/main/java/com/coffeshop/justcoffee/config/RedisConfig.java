package com.coffeshop.justcoffee.config;

import com.coffeshop.justcoffee.models.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer keys = new StringRedisSerializer();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    public RedisTemplate<String, Coffee> coffeeTemplate() {
        RedisTemplate<String, Coffee> coffeeTemplate = new RedisTemplate<>();
        RedisSerializer<Coffee> values = new Jackson2JsonRedisSerializer<Coffee>(Coffee.class);
        RedisSerializer keys = new StringRedisSerializer();
        coffeeTemplate.setValueSerializer(values);
        coffeeTemplate.setKeySerializer(keys);
        coffeeTemplate.setConnectionFactory(jedisConnectionFactory());
        return coffeeTemplate;
    }
}
