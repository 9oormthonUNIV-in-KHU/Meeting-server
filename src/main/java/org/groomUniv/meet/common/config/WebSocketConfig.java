package org.groomUniv.meet.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker // STOMP 메세징 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Bean
    public TaskScheduler brokerTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("wss-heartbeat-thread-");
        return scheduler;
    }

    //"pub" 엔드포인트로 발행(송신), "sub" 엔드포인트로 구독(수신)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/sub")
                .setHeartbeatValue(new long[]{3000L, 3000L})
                .setTaskScheduler(brokerTaskScheduler());
        config.setApplicationDestinationPrefixes("/app");
    }

    // stomp 엔드포인트
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info(" registerStompEndpoints() called");
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
        // Postman 사용시 SockJS가 필요없기 때문에 이런식으로 또 열어줘야함 그걸 위해 적음
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*");
    }
}
// ws는 클라이언트 연결 엔드포인트
