package org.project.onboarding.repository;

import org.project.onboarding.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, QuerydslPredicateExecutor<Task> {
    Task findByIdAndIsDeletedFalse(Long taskId);
}