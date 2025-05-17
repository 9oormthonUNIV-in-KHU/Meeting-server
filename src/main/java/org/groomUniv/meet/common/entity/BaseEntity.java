package org.groomUniv.meet.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 활성화
// 생성일자, 수정일자, 시간 관리에 대한 것은 공통적인 부분이라 BaseEntity를 통해 관리하고 상속받아서 사용하기 위한 용도
public class BaseEntity {
    // create 날짜 언제인지
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 누가 create 했는지
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;



}
