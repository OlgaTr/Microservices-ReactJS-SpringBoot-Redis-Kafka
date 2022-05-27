package com.coffeshop.justcoffee.config;

import com.coffeshop.justcoffee.models.Coffee;
import com.coffeshop.justcoffee.models.CoffeeOrder;
import com.coffeshop.justcoffee.models.Topping;
import com.coffeshop.justcoffee.models.User;
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

    @Bean
    public RedisTemplate<String, Topping> toppingTemplate() {
        RedisTemplate<String, Topping> toppingTemplate = new RedisTemplate<>();
        RedisSerializer<Topping> values = new Jackson2JsonRedisSerializer<Topping>(Topping.class);
        RedisSerializer keys = new StringRedisSerializer();
        toppingTemplate.setValueSerializer(values);
        toppingTemplate.setKeySerializer(keys);
        toppingTemplate.setConnectionFactory(jedisConnectionFactory());
        return toppingTemplate;
    }

    @Bean
    public RedisTemplate<String, CoffeeOrder> coffeeOrderTemplate() {
        RedisTemplate<String, CoffeeOrder> coffeeOrderTemplate = new RedisTemplate<>();
        RedisSerializer<CoffeeOrder> values = new Jackson2JsonRedisSerializer<CoffeeOrder>(CoffeeOrder.class);
        RedisSerializer keys = new StringRedisSerializer();
        coffeeOrderTemplate.setValueSerializer(values);
        coffeeOrderTemplate.setKeySerializer(keys);
        coffeeOrderTemplate.setConnectionFactory(jedisConnectionFactory());
        return coffeeOrderTemplate;
    }

    @Bean
    public RedisTemplate<String, User> userTemplate() {
        RedisTemplate<String, User> userTemplate = new RedisTemplate<>();
        RedisSerializer<User> values = new Jackson2JsonRedisSerializer<User>(User.class);
        RedisSerializer keys = new StringRedisSerializer();
        userTemplate.setValueSerializer(values);
        userTemplate.setKeySerializer(keys);
        userTemplate.setConnectionFactory(jedisConnectionFactory());
        return userTemplate;
    }
}
