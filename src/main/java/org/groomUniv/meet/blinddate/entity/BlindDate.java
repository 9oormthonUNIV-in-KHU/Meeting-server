package org.groomUniv.meet.blinddate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.common.entity.BaseEntity;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@Table(name = "blind_date")

public class BlindDate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blindDateId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member1_id")
    private Member member1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member2_id")
    private Member member2;

    // 채팅제한
    private Long chatLimit;

    //챗 엔티티 리스트로 관리하기
    @OneToMany(mappedBy = "blindDate", cascade = CascadeType.ALL)
    private List<BlindDateChat> chats = new ArrayList<>();
}
// JPA Auditing 설명
//@CreatedDate: 엔터티가 처음 생성된 날짜를 자동으로 기록합니다.
//
//@LastModifiedDate: 엔터티가 마지막으로 수정된 날짜를 자동으로 기록합니다.
//
//@CreatedBy: 엔터티를 처음 생성한 사용자를 자동으로 기록합니다.
//
//@LastModifiedBy: 엔터티를 마지막으로 수정한 사용자를 자동으로 기록합니다.