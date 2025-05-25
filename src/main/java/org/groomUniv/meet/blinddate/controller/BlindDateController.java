package org.groomUniv.meet.blinddate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.blinddate.dto.BlindDateChatDto;
import org.groomUniv.meet.blinddate.entity.BlindDateChat;
import org.groomUniv.meet.blinddate.service.BlindDateService;
import org.groomUniv.meet.chat.service.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;

@Slf4j

@RestController

public class BlindDateController {

    private final BlindDateService service;
    private final KafkaProducer<BlindDateChatDto> producer;

    public BlindDateController(BlindDateService service,
                                   KafkaProducer<BlindDateChatDto> producer) {
        this.service  = service;
        this.producer = producer;
    }

    // WebSocket(pub) 엔드포인트
    @MessageMapping("/blinddate/chat")
    public void send(BlindDateChatDto message) {
        BlindDateChatDto saved = service.save(message);
        producer.sendMessage("blinddate-chat-topic", saved);
    }

    // REST 히스토리 조회
    @GetMapping("/blinddate/chat/{chatroomId}")
    public ResponseEntity<List<BlindDateChatDto>> history(
            @PathVariable Long chatroomId) {
        return ResponseEntity.ok(service.loadHistory(chatroomId));
    }
}