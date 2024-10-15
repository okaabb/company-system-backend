package org.project.onboarding.businessLayers;


import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.QEmployee;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class EmployeeBusinessLayer {
    private final EmployeeRepository employeeRepository;

    public EmployeeBusinessLayer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeEntityById(Long employeeId) throws BusinessException {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId);
        if (isNull(employee))
            throw new BusinessException("Employee does not exist.", HttpStatus.NOT_FOUND);
        return employee;
    }
}
