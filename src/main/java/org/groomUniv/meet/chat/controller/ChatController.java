package org.groomUniv.meet.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.chat.dto.ChatMessage;
import org.groomUniv.meet.chat.service.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final KafkaProducer kafkaProducer;

    @MessageMapping("/chat") // pub
    public ResponseEntity<Void> greeting(@RequestBody ChatMessage chatMessage) throws Exception {
        Long chatroomId = chatMessage.getId();
        chatMessage.setId(chatroomId);
        kafkaProducer.sendMassage("chat-exchange", chatMessage);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/chat/{chatroomId}")
//    public ResponseEntity<ChatHistory> loadChatHistory(@PathVariable Long chatroomId) {
//        ChatHistory chatHistory = chatService.loadChatHistory(chatroomId);
//        return ResponseEntity.ok().body(chatHistory);
//    }
}
