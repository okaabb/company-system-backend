package org.project.onboarding.repository;

import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>, QuerydslPredicateExecutor<Training> {
}