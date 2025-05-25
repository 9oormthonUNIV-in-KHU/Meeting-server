package org.groomUniv.meet.blinddate.repository;

import org.groomUniv.meet.blinddate.entity.BlindDate;
import org.groomUniv.meet.blinddate.entity.BlindDateChat;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlindDateChatRepository extends JpaRepository<BlindDateChat, Long> {

    // 채팅 ID로 채팅 검색
    Optional<BlindDateChat> findById(Long blindDataChatId);

    // 특정 블라인드 채팅에 속한 모든 채팅 시간순으로
    List<BlindDateChat> findAllByBlindDateOrderByTimestampAsc(BlindDate blindDate);
    // 읽지 않은 메세지들 가져오기
    List<BlindDateChat> findAllByBlindDateAndReadStatusFalse(BlindDate blindDate);
    // 특정 sender 기반으로 조회하기
    List<BlindDateChat> findAllBySender(Member sender);

    List<BlindDateChat> findAllByBlindDate_BlindDateIdOrderByTimestampAsc(Long blindDateId);
}
