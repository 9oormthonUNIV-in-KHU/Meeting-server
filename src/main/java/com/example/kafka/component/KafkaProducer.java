package com.example.kafka.component;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaProducer {

private final KafkaTemplate<String, Object> kafkaTemplate;

public void sendMessage(String topic, ChatMessage chatMessage){
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonInString = "";
    try{
        jsonInString =
    }
}


}
