package org.groomUniv.meet.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.chat.dto.ChatMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "chat-exchange")
    public void consume(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            // String 메세지를 ChatMessage로 변환
            ChatMessage chatMessage = mapper.readValue(message, ChatMessage.class);

            // WebSocket을 통해 해당 채팅방으로 메세지 전송
            String destination = "/sub/chat/" + chatMessage.getId();
            template.convertAndSend(destination, chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
