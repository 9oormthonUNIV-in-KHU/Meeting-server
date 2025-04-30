package org.groomUniv.meet.blinddate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@Table(name = "blind_date")
@EntityListeners(AuditingEntityListener.class)
public class BlindDate {

@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blindDateId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member1_id")
private Member member1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member2_id")
private Member member2;

    @CreatedDate // 자동으로 엔티티가 처음 생성되었을 때 저장
private LocalDateTime createdAt;


private Long chatLimit;

    @OneToMany(mappedBy = "blindDate", cascade = CascadeType.ALL)
    private List<BlindDateChat> chats = new ArrayList<>();
}
