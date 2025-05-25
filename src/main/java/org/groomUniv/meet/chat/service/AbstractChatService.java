package org.groomUniv.meet.chat.service;

import org.groomUniv.meet.chat.dto.IMessage;
import org.groomUniv.meet.chat.dto.IMessageEntity;
import org.groomUniv.meet.chat.repository.ChatHistoryRepository;
import org.groomUniv.meet.chat.repository.ChatMessageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public abstract class AbstractChatService<T extends IMessage, E extends IMessageEntity> {


private final ChatHistoryRepository<E> repository;

protected AbstractChatService(ChatHistoryRepository<E> repository) {
    this.repository = repository;
}
public T save(T dto){
    E entity = toEntity(dto);
    E saved = repository.save(entity);
    return toDto(saved);
}

    public List<T> loadHistory(Long chatroomId) {
        return repository.findByChatroomIdOrderByTimestampAsc(chatroomId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

protected abstract E toEntity(T dto);
protected abstract T toDto(E entity);

}

//}
//public ChatMessageDto save(ChatMessageDto dto){
//    // 1) DTO → Entity
//    ChatMessage entity = new ChatMessage();
//    BeanUtils.copyProperties(dto, entity);
//
//    // 2) 저장
//    ChatMessage saved = chatMessageRepository.save(entity);
//
//    // 3) Entity → DTO
//    ChatMessageDto result = new ChatMessageDto();
//    BeanUtils.copyProperties(saved, result);
//    return result;
//}
//
//public List<ChatMessageDto> loadChatHistory(Long chatroomId){
//
//    return chatMessageRepository
//            .findByChatroomIdOrderByTimestampAsc(chatroomId)
//            .stream()
//            .map(entity -> {
//                ChatMessageDto dto = new ChatMessageDto();
//                BeanUtils.copyProperties(entity, dto);
//                return dto;
//            })
//            .collect(Collectors.toList());
//}
//}
