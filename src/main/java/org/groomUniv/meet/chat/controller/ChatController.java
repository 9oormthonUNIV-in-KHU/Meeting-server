package org.groomUniv.meet.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.groomUniv.meet.chat.entity.ChatMessage;
import org.groomUniv.meet.chat.service.ChatService;
import org.groomUniv.meet.chat.service.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller // HTTP가 아니라 웹소켓용으로
@RequiredArgsConstructor
public class ChatController {

    private final KafkaProducer kafkaProducer;
    private final ChatService chatService;
    @MessageMapping("/chat") // pub
    public void greeting(ChatMessageDto chatMessage) throws Exception {
        log.info("start");
        // DB에 저장
        ChatMessageDto saved = chatService.save(chatMessage);
        log.info("서비스에 잘 저장됨");
        //Kafka publish 과정
        kafkaProducer.sendMessage("chat-topic", saved);
        log.info("카프카로 잘 보냄");
    }
    // 특정 채팅방 ID의 히스토리를 조회해서 가져오기
    @GetMapping("/chat/{chatroomId}")
    public ResponseEntity<List<ChatMessageDto>> loadChatHistory(@PathVariable Long chatroomId) {
        List<ChatMessageDto> chatHistory = chatService.loadChatHistory(chatroomId);
        return ResponseEntity.ok().body(chatHistory);
    }
}
