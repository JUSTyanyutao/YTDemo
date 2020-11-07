package com.just.ytdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created on 2018-03-15 下午11:17.
 *
 * @yanyutao
 * @since 1.8
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("jdbc")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new SimpleModule());
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return mapper;
    }

//    @Primary
//    @Bean("redisTemplate")
//    public RedisTemplate getRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate template = new RedisTemplate();
//        template.setConnectionFactory(jedisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        template.setValueSerializer(genericJackson2JsonRedisSerializer);
//        template.setDefaultSerializer(genericJackson2JsonRedisSerializer);
//        return template;
//    }
}
