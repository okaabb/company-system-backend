package org.project.onboarding.repository;

import com.querydsl.core.types.dsl.StringPath;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.entity.QApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long>, QuerydslPredicateExecutor<Applicant>, QuerydslBinderCustomizer<QApplicant> {
    @Override
    default void customize(QuerydslBindings bindings, QApplicant root) {
        bindings.bind(String.class)
                .first((StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(root.email);
    }

    Applicant findByEmailAndIsDeletedFalse(String email);

    Applicant findByMobileNumberAndIsDeletedFalse(String MobileNumber);

    Applicant findByEmailAndIsDeletedFalseAndIdIsNot(String email, Long id);

    Applicant findByMobileNumberAndIsDeletedFalseAndIdIsNot(String MobileNumber, Long id);

    Applicant findByIdAndIsDeletedFalse(Long id);
}