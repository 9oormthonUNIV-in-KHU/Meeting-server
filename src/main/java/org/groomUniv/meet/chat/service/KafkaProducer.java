package org.groomUniv.meet.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, ChatMessageDto chatMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = objectMapper.writeValueAsString(chatMessage);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }
}
