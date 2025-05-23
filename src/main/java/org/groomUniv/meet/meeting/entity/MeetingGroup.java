package org.groomUniv.meet.meeting.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.oauth.entity.Member;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
public class MeetingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region; // 지역 필터용
    private int averageAge;
    private Long averageHeight;
    private String dominantMajor;

    @OneToMany(mappedBy = "meetingGroup")
    private List<Member> members = new ArrayList<>();

    // 이전에 같이 미팅했던 그룹들 리스트로 저장
    @ElementCollection
    @CollectionTable(name = "matched_group_ids", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "matched_group_id")
    private List<Long> matchedGroupIds = new ArrayList<>();

    public void addMember(Member member) {
        this.members.add(member);
        member.assignMeetingGroup(this);
    }

}
