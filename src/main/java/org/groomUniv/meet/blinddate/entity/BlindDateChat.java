package org.groomUniv.meet.blinddate.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.groomUniv.meet.chat.dto.IMessageEntity;
import org.groomUniv.meet.common.entity.BaseEntity;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
public class BlindDateChat extends BaseEntity implements IMessageEntity {

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
    private Instant timestamp;

    @Override
    @Transient                         // JPA에서 컬럼으로 보지 말고, JPQL 속성으로만 사용
    public Long getChatroomId() {
        return blindDate.getBlindDateId();
    }
    @Override
    @Transient
    public Instant getTimestamp() {
        return timestamp;
    }

}
