package org.groomUniv.meet.oauth.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Entity
@Getter
@Table(name = "school")
public class School {
@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long school_id;
// 학교이름
private String name;
// 학과
private String domain;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();
}
