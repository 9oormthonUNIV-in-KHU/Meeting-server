package org.groomUniv.meet.meeting.service;

import lombok.extern.slf4j.Slf4j;
import org.groomUniv.meet.meeting.dto.request.ConditionRequest;
import org.groomUniv.meet.meeting.dto.response.SearchMeetingResponse;
import org.groomUniv.meet.meeting.entity.MeetingGroup;
import org.groomUniv.meet.meeting.repository.MeetingGroupRepository;
import org.groomUniv.meet.meeting.repository.MeetingRepository;
import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MeetingService {

    private final MemberRepository memberRepository;
    private final MeetingGroupRepository meetingGroupRepository;

    public MeetingService(MemberRepository memberRepository, MeetingGroupRepository meetingGroupRepository) {
        this.memberRepository = memberRepository;
        this.meetingGroupRepository = meetingGroupRepository;
    }

    //1. 멤버 ID 목록으로 새로운 미팅 그룹 생성
    public MeetingGroup createMeetingGroup(List<Long> memberIds, String region) {

        List<Long> sortedInputIds = new ArrayList<>(memberIds);
        Collections.sort(sortedInputIds);
        // 현재조합으로 이미 만들어진 그룹인지 확인
        List<MeetingGroup> allGroups = meetingGroupRepository.findAll();

        for(MeetingGroup meetingGroup : allGroups) {
            List<Long> groupMemberIds = meetingGroup.getMembers().stream()
                    .map(Member::getMemberId)
                    .sorted()
                    .collect(Collectors.toList());

            if (groupMemberIds.equals(sortedInputIds)) {
                return meetingGroup; // 기존 그룹이 이미 있음 -> 재사용
            }
        }

        List<Member> members = memberRepository.findAllById(memberIds);

        int averageAge = (int) members.stream().mapToLong(Member::getAge).average().orElse(0);
        long averageHeight = (long) members.stream().mapToLong(Member::getHeight).average().orElse(0);
        String dominantMajor = members.stream()
                .collect(Collectors.groupingBy(Member::getMajor, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("unknown");

        MeetingGroup group = new MeetingGroup();
        group.setRegion(region);
        group.setAverageAge(averageAge);
        group.setAverageHeight(averageHeight);
        group.setDominantMajor(dominantMajor);

        for (Member member : members) {
            group.addMember(member); // 이 메서드는 MeetingGroup 내부에서 members.add() 수행
        }

        return meetingGroupRepository.save(group);
    }


    // 사용자가 미팅 탐색 알고리즘 할 때 검색을 돕기 위한 특정 조건들, 검색 알고리즘
    public SearchMeetingResponse searchMeeting(ConditionRequest request) {



        List<Long> myMemberIds = new ArrayList<>(request.getMemberIds());
        Collections.sort(myMemberIds);

       // 모든 그룹 찾아오기
        List<MeetingGroup> allGroups = meetingGroupRepository.findAll();
        // 내가 포함된 그룹 찾기 (현재 멤버들이 포함된 그룹)
        MeetingGroup myGroup = allGroups.stream()
                .filter(g -> {
                    List<Long> gIds = g.getMembers().stream()
                            .map(Member::getMemberId)
                            .sorted()
                            .collect(Collectors.toList());
                    return gIds.equals(myMemberIds);
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버들로 구성된 그룹이 존재하지 않습니다."));



        // 상대방 매칭 과정
        MeetingGroup matchedGroup = allGroups.stream()
                .filter(g -> !g.getId().equals(myGroup.getId())) // 자기 자신 제외
                .filter(g -> !myGroup.getMatchedGroupIds().contains(g.getId())) // 이전에 만난 그룹 제외
                .filter(g -> g.getRegion().equalsIgnoreCase(request.getLocation()))
                .filter(g -> g.getAverageAge() >= request.getMinAge() && g.getAverageAge() <= request.getMaxAge())
                .findFirst()
                .orElse(null);

        if(matchedGroup == null) {
            throw new IllegalArgumentException("매치 성사 안되었습니다");
        }

        // 서로 matchedGroupIds에 추가
        // 서로 매치 성사되었을 때의 경우
        if (!myGroup.getMatchedGroupIds().contains(matchedGroup.getId())) {
            myGroup.getMatchedGroupIds().add(matchedGroup.getId());
        }
        if (!matchedGroup.getMatchedGroupIds().contains(myGroup.getId())) {
            matchedGroup.getMatchedGroupIds().add(myGroup.getId());
        }


        meetingGroupRepository.save(myGroup);
        meetingGroupRepository.save(matchedGroup);

        return new SearchMeetingResponse(
                matchedGroup.getId(),
                matchedGroup.getRegion(),
                matchedGroup.getAverageAge(),
                matchedGroup.getAverageHeight(),
                matchedGroup.getDominantMajor()
        );

    }
}

