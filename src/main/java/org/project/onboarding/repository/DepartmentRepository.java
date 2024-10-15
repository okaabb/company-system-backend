package org.project.onboarding.repository;

import com.querydsl.core.types.dsl.StringPath;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.QDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, QuerydslPredicateExecutor<Department>, QuerydslBinderCustomizer<QDepartment> {
    @Override
    default void customize(QuerydslBindings bindings, QDepartment root) {
        bindings.bind(String.class)
                .first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    Department findByIdAndIsDeletedFalse(Long id);

    Department findByNameAndIsDeletedFalseAndIdIsNot(String name, Long myId);

    Department findByNameAndIsDeletedFalse(String name);
}
