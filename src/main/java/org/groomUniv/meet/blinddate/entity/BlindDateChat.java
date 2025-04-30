package org.groomUniv.meet.blinddate.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.groomUniv.meet.common.entity.BaseEntity;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity
public class BlindDateChat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blindDataChatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blind_date_id")
    private BlindDate blindDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member sender;

    private String message;

    private Boolean readStatus;
}
