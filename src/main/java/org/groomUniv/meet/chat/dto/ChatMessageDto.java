package org.groomUniv.meet.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChatMessageDto {
    private Long id;
    private Long chatroomId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
}