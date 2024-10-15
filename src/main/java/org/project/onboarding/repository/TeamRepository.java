package org.project.onboarding.repository;

import org.project.onboarding.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, QuerydslPredicateExecutor<Team> {
    Team findByIdAndIsDeletedFalse(Long id);

    Team findByNameAndIsDeletedFalse(String teamName);

    Team findByNameAndIsDeletedFalseAndIdIsNot(String teamName, Long id);
}