package org.groomUniv.meet.blinddate.repository;

import org.groomUniv.meet.blinddate.entity.BlindDate;
import org.groomUniv.meet.blinddate.entity.BlindDateChat;
import org.groomUniv.meet.oauth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlindDateRepository extends JpaRepository<BlindDateChat,Long> {
    // 특정 두 멤버 기반의 챗 가져오기
    Optional<BlindDate> findByMember1AndMember2(Member member1, Member member2);
    // 특정 멤버중 하나만 있어도 가져오는 함수
    @Query("SELECT b FROM BlindDate b WHERE b.member1 = :member OR b.member2 = :member")
    List<BlindDate> findAllByMember(@Param("member") Member member);
    // 채팅 ID를 통해 BlindDate 찾기 - 쿼리 기반
    @Query("SELECT b FROM BlindDate b JOIN b.chats c WHERE c.messageId = :chatId")
    Optional<BlindDate> findByChatMessageId(@Param("chatId") Long chatId);
}
