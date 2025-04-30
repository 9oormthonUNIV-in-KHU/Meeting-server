package org.groomUniv.meet.meeting.repository;

import org.groomUniv.meet.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    //미팅 Id 기반으로 검색
    Optional<Meeting> findById(Long meetingId);
    // 생성한 사람 기반으로 검색
    List<Meeting> findAllByCreatedBy(String createdBy);
    // 미팅 내용으로 검색  , 키워드 기반 욕, 문제되는 발언 필터링
    List<Meeting> findByMeetingDetailsContaining(String keyword);
}
