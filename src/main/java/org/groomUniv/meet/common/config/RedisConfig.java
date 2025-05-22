package org.groomUniv.meet.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    //Redis와 연결하기 위한 Connection Factory Bean 생성(Redis 연결을 관리해주는 핵심 컴포넌트)
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(); // Redis에 단일 인스턴스로 연결하기 위한 Standalone 설정 객체 생성
        redisStandaloneConfiguration.setHostName(host); // Redis 서버 주소 설정
        redisStandaloneConfiguration.setPort(port); // Redis 서버 포트 설정

        // Lettuce 클라이언트를 사용해 연결 팩토리 생성
        // Jedis보다 성능이 좋고 넌블로킹 방식이라 많이 사용
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    // RedisTemplate은 Redis에 데이터를 저장/조회/삭제하는 핵심 도구(커스터마이징을 통해 키/값 직렬화 방식 지정 가능)
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();    // RedisTemplate 객체 생성(객체를 저장할 땐 Object로)
        redisTemplate.setConnectionFactory(redisConnectionFactory());   // Redis 연결 팩토리 설정(위에서 만든 redisConnectionFactory())
        redisTemplate.setKeySerializer(new StringRedisSerializer());    // 키를 문자열로 직렬화 (인코딩 방식 UTF-8)
        redisTemplate.setValueSerializer(new StringRedisSerializer());  // 값을 문자열로 직렬화(기본은 jdk 직렬화이므로 명시적으로 설정 필요)
        redisTemplate.setEnableTransactionSupport(true);    // 트랜잭션 활성화(ex. 여러 Redis 작업을 하나의 트랜잭션으로 묶고 실패시 롤백)
        return redisTemplate;
    }
}
