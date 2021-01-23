package com.just.ytdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;
import java.time.Duration;

/**
 * Created on 2018-03-15 下午11:17.
 *
 * @yanyutao
 * @since 1.8
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "jdbc")
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

    @Bean
    public StringRedisTemplate redisTemplate(
            @Value("${spring.redis.database}") int database,
            @Value("${spring.redis.host}") String hostName,
            @Value("${spring.redis.port}") int port,
            @Value("${spring.redis.password}") String password) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(jedisConnectionFactory(database, hostName, port, password));
        temple.setKeySerializer(new StringRedisSerializer());
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        temple.setValueSerializer(genericJackson2JsonRedisSerializer);
        temple.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        return temple;
    }
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





    protected JedisConnectionFactory jedisConnectionFactory(int database, String hostName, int port, String password) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s
        // connection
        // timeout
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    }



}
