package org.groomUniv.meet.chat.repository;

import org.groomUniv.meet.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatroomIdOrderByTimestampAsc(Long chatroomId);
}
