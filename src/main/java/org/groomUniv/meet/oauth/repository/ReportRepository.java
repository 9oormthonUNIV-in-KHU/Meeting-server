package org.groomUniv.meet.oauth.repository;

import org.groomUniv.meet.oauth.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
// 신고자 ID로 조회
    List<Report> findAllByReporterId(String reporterId);
// 신고당한 사람의 ID로 조회
    List<Report> findAllByReportedMemberId(String reportedMemberId);
// 보상 받은 report들 혹은 못받은 report들 기준
    List<Report> findAllByRewardFlag(boolean rewardFlag);
// 특정 날짜 기준으로 그 기준 이후의 report들만 가져오기
    List<Report> findAllByCreatedAtAfter(String createdAt);
}
