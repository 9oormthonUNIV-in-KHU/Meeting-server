package org.groomUniv.meet.meeting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.blinddate.entity.BlindDateChat;
import org.groomUniv.meet.common.entity.BaseEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Meeting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;


    private String meetingDetails;


    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingChat> chats = new ArrayList<>();
}

