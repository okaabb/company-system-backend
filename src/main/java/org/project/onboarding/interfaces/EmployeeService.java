package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.employee.*;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface EmployeeService {
    void saveEmployee(PostEmployeeDTO dto) throws BusinessException;

    PaginationDTO<ListEmployeeDTO> getAllEmployees(Map<String, String> search, Pageable pageable);

    GetEmployeeDTO getEmployeeById(Long id) throws BusinessException;

    void updateEmployee(Long id, UpdateEmployeeDTO updatedEmployee) throws BusinessException;

    void deleteEmployee(Long id) throws BusinessException;

}
