package org.groomUniv.meet.meeting.repository;

import org.groomUniv.meet.meeting.entity.MeetingChat;
import org.groomUniv.meet.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingChatRepository extends JpaRepository<MeetingChat, Long> {
    Optional<MeetingChat> findById(Long meetingChatId);
    List<MeetingChat> findAllByMeeting_IdOrderByCreatedAtAsc(Long meetingId);

}
