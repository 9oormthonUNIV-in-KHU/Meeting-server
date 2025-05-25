package org.groomUniv.meet.chat.repository;

import org.groomUniv.meet.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoRepositoryBean

public interface ChatHistoryRepository<E> extends JpaRepository<E, Long> {
    List<E> findByChatroomIdOrderByTimestampAsc(Long chatroomId);
}
