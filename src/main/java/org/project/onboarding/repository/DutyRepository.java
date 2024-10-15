package org.project.onboarding.repository;

import org.project.onboarding.entity.Duty;
import org.project.onboarding.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Long>, QuerydslPredicateExecutor<Duty> {
    Duty findByIdAndIsDeletedFalse(Long id);
}