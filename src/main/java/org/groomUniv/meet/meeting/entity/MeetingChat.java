package org.groomUniv.meet.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class MeetingChat {

@Id@GeneratedValue
    private Long meetingChatId;

@CreatedDate
    private LocalDateTime createdAt;

private String message;

private String senderId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
private Meeting meeting;

}
// 생성 가이드라인
//MeetingChat chat = new MeetingChat();
//chat.setMeeting(meeting); // 여기서 meeting은 기존에 조회한 Meeting 객체
//chat.setSenderId(1L);
//chat.setMessage("안녕하세요!");
//chat.setTimestamp(LocalDateTime.now());
