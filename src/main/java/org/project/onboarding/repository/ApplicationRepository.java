package org.project.onboarding.repository;

import org.project.onboarding.entity.Applicant;
import org.project.onboarding.entity.Application;
import org.project.onboarding.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>, QuerydslPredicateExecutor<Application> {
    Application findByIdAndIsDeletedFalse(Long id);
}