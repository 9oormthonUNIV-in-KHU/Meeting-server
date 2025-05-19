package org.groomUniv.meet.chat.service;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.chat.entity.ChatMessage;
import org.groomUniv.meet.chat.dto.ChatMessageDto;
import org.groomUniv.meet.chat.repository.ChatMessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
private final ChatMessageRepository chatMessageRepository;

public ChatMessageDto save(ChatMessageDto dto){
    // 1) DTO → Entity
    ChatMessage entity = new ChatMessage();
    BeanUtils.copyProperties(dto, entity);

    // 2) 저장
    ChatMessage saved = chatMessageRepository.save(entity);

    // 3) Entity → DTO
    ChatMessageDto result = new ChatMessageDto();
    BeanUtils.copyProperties(saved, result);
    return result;
}

public List<ChatMessageDto> loadChatHistory(Long chatroomId){

    return chatMessageRepository
            .findByChatroomIdOrderByTimestampAsc(chatroomId)
            .stream()
            .map(entity -> {
                ChatMessageDto dto = new ChatMessageDto();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            })
            .collect(Collectors.toList());
}
}
