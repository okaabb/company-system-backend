package org.project.onboarding.repository;

import org.project.onboarding.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {
    Employee findByIdAndIsDeletedFalse(Long id);

    Employee findByUsernameAndIsDeletedFalse(String username);

    Employee findByEmailAndIsDeletedFalse(String email);

    Employee findByMobileNumberAndIsDeletedFalse(String mobileNumber);

    Employee findByNationalIdAndIsDeletedFalse(String nationalId);

    Employee findByUsernameAndIsDeletedFalseAndIdIsNot(String username, Long id);

    Employee findByEmailAndIsDeletedFalseAndIdIsNot(String email, Long id);

    Employee findByMobileNumberAndIsDeletedFalseAndIdIsNot(String mobileNumber, Long id);

    Employee findByNationalIdAndIsDeletedFalseAndIdIsNot(String nationalId, Long id);
}