package org.groomUniv.meet.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.chat.dto.IMessage;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingChatDto implements IMessage {

    private Long meetingId;
    private Long senderId;
    private String message;
    private LocalDateTime createdAt;

    @Override
    public Long getChatroomId() {
        return meetingId;
    }

    @Override
    public java.time.Instant getTimestamp() {
        // LocalDateTime → Instant 로 변환 (예: 시스템 기본 ZoneId)
        return createdAt.atZone(java.time.ZoneId.systemDefault()).toInstant();
    }
}
