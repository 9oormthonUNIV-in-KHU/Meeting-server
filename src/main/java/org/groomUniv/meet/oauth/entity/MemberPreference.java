package org.groomUniv.meet.oauth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MemberPreference {
@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preference_id;

    @OneToOne(mappedBy = "memberPreference", fetch = FetchType.LAZY)
    private Member member;

private Long desired_age_min;
private Long desired_age_max;
private Long desired_height_min;
private Long desired_height_max;
private String desired_school;
private String desired_major;
private String additional_criteria;

}
