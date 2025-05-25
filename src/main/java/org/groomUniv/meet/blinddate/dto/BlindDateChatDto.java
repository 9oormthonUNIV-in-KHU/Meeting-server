package org.groomUniv.meet.blinddate.dto;

import lombok.Data;
import org.groomUniv.meet.chat.dto.IMessage;

import java.time.Instant;

// src/main/java/org/groomUniv/meet/chat/dto/BlindDateChatDto.java


import java.time.Instant;
@Data
public class BlindDateChatDto implements IMessage {
    private Long chatroomId;   // 실제 blindDateId
    private String message;
    private Instant timestamp;

    private Long senderId;
    public BlindDateChatDto(Long chatroomId, String message, Instant timestampm, Long senderId) {
        this.chatroomId = chatroomId;
        this.message    = message;
        this.timestamp  = timestamp;
        this.senderId = senderId;
    }

}
