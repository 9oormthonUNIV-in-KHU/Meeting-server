package org.groomUniv.meet.chat.dto;
// 추상화로 받을 객체

import java.time.Instant;
import java.time.LocalDateTime;

public interface IMessage {

    Long getChatroomId();
    Instant getTimestamp();
}
