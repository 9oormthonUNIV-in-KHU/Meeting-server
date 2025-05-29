package org.groomUniv.meet.chat.controller;

import kotlin.reflect.KFunction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.groomUniv.meet.chat.dto.IMessage;
import org.groomUniv.meet.chat.dto.IMessageEntity;
import org.groomUniv.meet.chat.service.AbstractChatService;

import org.groomUniv.meet.chat.service.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller // HTTP가 아니라 웹소켓용으로
@RequiredArgsConstructor
public abstract class AbstractChatController<T extends IMessage, E extends IMessageEntity> {
    private final AbstractChatService<T,E > chatService;
    private final KafkaProducer kafkaProducer;

    @MessageMapping("${chat.mapping}")
    public void send(T message){
        T saved = chatService.save(message);
        kafkaProducer.sendMessage(getTopic(), saved);
    }
    @GetMapping("${chat.rest-path}/{chatroomId}")
    public ResponseEntity<List<T>> history(@PathVariable Long chatroomId){
        return ResponseEntity.ok(chatService.loadHistory(chatroomId));
    }
    protected abstract String getTopic();
}
