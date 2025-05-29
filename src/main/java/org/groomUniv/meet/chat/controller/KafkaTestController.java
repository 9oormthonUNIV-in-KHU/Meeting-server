package org.groomUniv.meet.chat.controller;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.groomUniv.meet.chat.dto.IMessage;
import org.groomUniv.meet.chat.service.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test")
public class KafkaTestController <T extends IMessage> {


    private final KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public ResponseEntity<Void> publish(@RequestBody T  msg) {
        kafkaProducer.sendMessage("chat-topic", msg);
        return ResponseEntity.ok().build();
    }
}
