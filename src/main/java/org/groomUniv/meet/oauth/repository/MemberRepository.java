package org.groomUniv.meet.oauth.repository;

import org.groomUniv.meet.oauth.entity.Member;
import org.groomUniv.meet.oauth.entity.School;
import org.groomUniv.meet.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>  {

Optional<Member> findByEmail(String email);
Optional<Member> findByName(String name);
//학교별 모든 멤버 가져오는

    Optional<Member> findAllBySchool(School school);

    boolean existsByEmail(String email);
}
// 간단한 사용법
//School school = schoolRepository.findByName("경희대학교").orElseThrow();
//List<Member> members = memberRepository.findAllBySchool(school);


