package org.groomUniv.meet.oauth.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "member")
public class Member {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long memberId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "preference_id")  // User 테이블이 외래키를 가진다
    private MemberPreference memberPreference;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id") // User 테이블에 school_id 외래키 컬럼 생긴다
    private School school;


private String email;
// password는 security로 암홓화해야함
private String password;

private String name;
private String image;

private Long height;
private Long age;
private String biography;
private String major;

private boolean emailVerified;
}
