package org.groomUniv.meet.payment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_payment")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문 고유번호 (클라이언트에서 생성한 값)
    @Column(unique = true)
    private String merchantUid;

    // iamport에서 반환하는 결제 고유번호
    private String impUid;

    // 결제 금액
    private BigDecimal amount;

    // 결제 상태 (예: "PENDING", "VERIFIED", "FAILED")
    private String status;

    // 결제 요청 시각
    private LocalDateTime requestTime;

    // 검증 완료 시각
    private LocalDateTime verifiedTime;
}
