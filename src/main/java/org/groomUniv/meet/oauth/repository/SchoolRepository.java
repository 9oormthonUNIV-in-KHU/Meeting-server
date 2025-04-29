package org.groomUniv.meet.oauth.repository;

import org.groomUniv.meet.oauth.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  SchoolRepository extends JpaRepository<School, Long> {
Optional<School> findByName(String name);

}
