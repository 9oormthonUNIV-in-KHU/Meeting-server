package org.groomUniv.meet.oauth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Report {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long reportId;

private String reportReason;
@CreatedDate
private LocalDateTime createdAt;

private boolean rewardFlag;
private String reporterId;
private String reportedMemberId;

}
