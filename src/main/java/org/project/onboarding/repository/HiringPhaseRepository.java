package org.project.onboarding.repository;

import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.HiringPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringPhaseRepository extends JpaRepository<HiringPhase, Long>, QuerydslPredicateExecutor<HiringPhase> {
    HiringPhase findByIdAndIsDeletedFalse(Long hiringPhaseId);
}