package org.groomUniv.meet.oauth.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Entity
@Data
@Table(name = "school")
public class School {
@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long school_id;

private String name;
private String domain;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();
}
