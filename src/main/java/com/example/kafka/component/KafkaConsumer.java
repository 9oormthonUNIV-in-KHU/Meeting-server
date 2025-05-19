package com.example.kafka.component;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "chat-exchange")
public void listener(String message) {
ObjectMapper mapper = new ObjectMapper();
try{
    ChatMessage chatMessage = mapper.readValue(message,ChatMessage.class);

    String destination = "/sub/chat" + chatMessage.getId();
    template.convertAndSend(destination,chatMessage);

}
catch(Exception e){
    e.printStackTrace();
}

}
}
