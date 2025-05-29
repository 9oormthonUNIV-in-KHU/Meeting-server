package org.groomUniv.meet.chat.dto;

import java.time.Instant;

public interface IMessageEntity {
    Long getChatroomId();
    Instant getTimestamp();
}
