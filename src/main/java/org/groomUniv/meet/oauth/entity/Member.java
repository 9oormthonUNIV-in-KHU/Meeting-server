package org.groomUniv.meet.oauth.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.groomUniv.meet.meeting.entity.MeetingGroup;

@NoArgsConstructor
@Entity
@Getter
@Data
@Table(name = "member")
public class Member {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long memberId;

@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "preference_id")
private MemberPreference memberPreference;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "school_id")
private School school;


private String email;
// password는 security로 암홓화해야함
private String password;

private String name;
private String image;
//사는 지역
private String location;
private Long height;
private Long age;
private String biography;
private String major;

private boolean emailVerified;

    // MeetingGroup 때문에 추가함

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_group_id")
    private MeetingGroup meetingGroup;

    public void assignMeetingGroup(MeetingGroup group) {
        this.meetingGroup = group;
    }
}
