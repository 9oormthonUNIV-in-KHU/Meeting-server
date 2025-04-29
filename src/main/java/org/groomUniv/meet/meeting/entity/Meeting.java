package org.groomUniv.meet.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.blinddate.entity.BlindDateChat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor

public class Meeting {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_id;

@CreatedDate
    private LocalDateTime createdAt;

private String meetingDetails;

@CreatedBy
private String createdBy;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingChat> chats = new ArrayList<>();
}

