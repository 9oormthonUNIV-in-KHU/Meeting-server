package org.groomUniv.meet.blinddate.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.groomUniv.meet.oauth.entity.Member;

import java.time.LocalDateTime;

@Data
@Entity
public class BlindDateChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blind_date_id")
    private BlindDate blindDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member sender;


    private String message;

    private LocalDateTime timestamp;

    private Boolean readStatus;
}
// JPA Auditing 설명
//@CreatedDate: 엔터티가 처음 생성된 날짜를 자동으로 기록합니다.
//
//@LastModifiedDate: 엔터티가 마지막으로 수정된 날짜를 자동으로 기록합니다.
//
//@CreatedBy: 엔터티를 처음 생성한 사용자를 자동으로 기록합니다.
//
//@LastModifiedBy: 엔터티를 마지막으로 수정한 사용자를 자동으로 기록합니다.