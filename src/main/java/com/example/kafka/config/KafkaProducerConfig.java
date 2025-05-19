package com.example.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
Map<String, Object> config = new HashMap<>();
//Producer가 처음으로 연결한 Kafka 브로커의 위치 설정
config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//Producer가 Key와 Value값의 데이터를 Kafka 브로커로 전송하기 전에 데이터를 byte arrary로 변환하는데 사용하는 직렬화 메커니즘 설정
        //네트워크로 데이터를 전송하니까 객체를 byte array로 변환하는 과정이 필요

config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

return new DefaultKafkaProducerFactory<>(config);

    }
    //KafkaTemplate은 Spring Kafka에서 제공하는 Kafka Producer를 Wrapping 한 클래스
    // 여러 메소드 정의
    @Bean
public KafkaTemplate<String, Object> kafkaTemplate() {
return new KafkaTemplate<>(producerFactory());
}
}