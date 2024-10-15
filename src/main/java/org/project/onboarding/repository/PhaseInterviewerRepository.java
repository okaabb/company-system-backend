package org.project.onboarding.repository;

import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.PhaseInterviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseInterviewerRepository extends JpaRepository<PhaseInterviewer, Long>, QuerydslPredicateExecutor<PhaseInterviewer> {
    PhaseInterviewer findByIdAndIsDeletedFalse(Long phaseInterviewerId);
}