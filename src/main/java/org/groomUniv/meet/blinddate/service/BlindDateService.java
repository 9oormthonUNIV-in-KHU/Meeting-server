package org.groomUniv.meet.blinddate.service;

import lombok.RequiredArgsConstructor;
import org.groomUniv.meet.blinddate.dto.BlindDateChatDto;
import org.groomUniv.meet.blinddate.entity.BlindDate;
import org.groomUniv.meet.blinddate.entity.BlindDateChat;
import org.groomUniv.meet.blinddate.repository.BlindDateChatRepository;
import org.groomUniv.meet.blinddate.repository.BlindDateRepository;
import org.groomUniv.meet.chat.service.AbstractChatService;
import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.text.CollationElementIterator;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Transactional
@Service
// BlindDate에서 어떻게 매칭시킬지 search랑 성사 로직 구현 생각해야함
public class BlindDateService {

    private final BlindDateChatRepository blindDateChatRepository;
    private final BlindDateRepository blindDateRepository;
    private final MemberRepository memberRepository;




    public BlindDateChatDto save(BlindDateChatDto dto){


        BlindDate bd = blindDateRepository.findById(dto.getChatroomId())
                .orElseGet(() -> {
                    BlindDate newBd = new BlindDate();


                    // senderId로 Member 1 조회
                    Member m1 = memberRepository
                            .findById(dto.getSenderId())
                            .orElseThrow(() -> new RuntimeException("Sender not found"));
                    newBd.setMember1(m1);

                    // recipientId로 Member 2 조회
                    Member m2 = memberRepository
                            .findById(dto.getSenderId())
                            .orElseThrow(() -> new RuntimeException("Sender not found"));
                    newBd.setMember2(m2);

                    // 기본 채팅 제한 (예: 100개)
                    newBd.setChatLimit(100L);

                    return blindDateRepository.save(newBd);
                });
        // 2) 메시지 엔티티로 변환·저장
        BlindDateChat bdc = new BlindDateChat();
        bdc.setBlindDate(bd);
        bdc.setMessage(dto.getMessage());
        bdc.setTimestamp(
                dto.getTimestamp() != null
                        ? dto.getTimestamp()
                        : Instant.now()
        );

        BlindDateChat saved = blindDateChatRepository.save(bdc);

        // 3) DTO로 리턴
        return toDto(saved);
    }

    public List<BlindDateChatDto> loadHistory(Long chatroomId){
        blindDateRepository.findById(chatroomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 blinddateId"));

        return blindDateChatRepository
                .findAllByBlindDate_BlindDateIdOrderByTimestampAsc(chatroomId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());



    }

    private BlindDateChatDto toDto(BlindDateChat e){
        return new BlindDateChatDto(
                e.getBlindDate().getBlindDateId(),
                e.getMessage(),
                e.getTimestamp(),
                e.getSender() != null ? e.getSender().getMemberId() : null


        );
    }


}