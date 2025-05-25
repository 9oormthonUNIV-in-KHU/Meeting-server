package org.groomUniv.meet.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.groomUniv.meet.chat.dto.IMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Service

public abstract class KafkaConsumer <T extends IMessage> {
    // WebSocket을 이용해 클라이언트에게 메세지를 보내기 위한 객체
    private final SimpMessagingTemplate template;

    private final ObjectMapper mapper = new ObjectMapper();

    protected KafkaConsumer(SimpMessagingTemplate template) {
        this.template = template;
    }

    // 서브클래스에서 토픽 이름만 오버라이드
    protected abstract String topic();

    // 서브클래스에서 DTO 타입만 지정
    protected abstract Class<T> type();

    @KafkaListener(
            topics = "#{__listener.topic()}",
            groupId = "chat-group",
            containerFactory = "stringListenerContainerFactory"
    )
    public void consume(String json) {
        try {
            T msg = mapper.readValue(json, type());
            String dest = "/sub" + getDestinationPath(msg);
            template.convertAndSend(dest, msg);
        } catch (Exception e) {
            log.error("메시지 처리 실패: {}", e.getMessage(), e);
        }
    }

    // 서브클래스별로 WebSocket 경로 지정
    protected abstract String getDestinationPath(T message);
}