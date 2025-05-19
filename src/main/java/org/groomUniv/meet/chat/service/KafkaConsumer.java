package org.groomUniv.meet.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    // WebSocket을 이용해 클라이언트에게 메세지를 보내기 위한 객체
    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "chat-exchange")
    public void consume(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            // String 메세지를 ChatMessage로 변환
            ChatMessageDto chatMessage = mapper.readValue(message, ChatMessageDto.class);
            log.info("kafka test consume");
            // WebSocket을 통해 해당 채팅방으로 메세지 전송
            String destination = "/sub/chat/" + chatMessage.getId();
            template.convertAndSend(destination, chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
