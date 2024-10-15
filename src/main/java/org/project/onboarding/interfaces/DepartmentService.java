package org.project.onboarding.interfaces;

import org.project.onboarding.dto.department.DepartmentPaginationDTO;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface DepartmentService {

    void saveDepartment(DepartmentDTO dto) throws BusinessException;

    DepartmentPaginationDTO getAllDepartments(Map<String, String> search, Pageable pageable);

    GetDepartmentDTO getDepartmentById(Long id) throws BusinessException;

    void updateDepartment(Long id, DepartmentDTO dto) throws BusinessException;

    void deleteDepartment(Long id) throws BusinessException;

    void checkNameUniquenessOnUpdate(String departmentName, Long id) throws BusinessException;

    void checkNameUniquenessOnSave(String departmentName) throws BusinessException;

    Department getDepartmentWithName(String departmentName) throws BusinessException;
}
