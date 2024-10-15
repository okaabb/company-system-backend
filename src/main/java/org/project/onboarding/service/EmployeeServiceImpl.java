package org.project.onboarding.service;

import lombok.RequiredArgsConstructor;
import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.employee.*;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.EmployeeService;
import org.project.onboarding.mapper.EmployeeMapper;
import org.project.onboarding.predicates.Employee.EmployeePredicateBuilder;
import org.project.onboarding.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.createPageResponse;
import static org.project.onboarding.helperFunctions.PredicatesHelper.isPageableKey;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentServiceImpl departmentServiceImpl;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final EmployeeBusinessLayer employeeBusinessLayer;

    @Override
    public void saveEmployee(PostEmployeeDTO employeeDTO) throws BusinessException {
        Employee employee = employeeMapper.mapEmployeeDTOtoEmployeeEntity(employeeDTO);

        checkEmailUniquenessOnSave(employeeDTO.getEmail());
        checkMobileNumberUniquenessOnSave(employeeDTO.getMobileNumber());
        checkUsernameUniquenessOnSave(employeeDTO.getUsername());
        checkNationalIdUniquenessOnSave(employeeDTO.getNationalId());

        String departmentName = employeeDTO.getDepartment();
        if (!isNull(departmentName)) {
            Department department = departmentServiceImpl.getDepartmentWithName(departmentName);
            employee.setDepartment(department);
        }
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    private void checkEmailUniquenessOnSave(String email) throws BusinessException {
        Employee employeeWithEmail = employeeRepository.findByEmailAndIsDeletedFalse(email);
        if (!isNull(employeeWithEmail))
            throw new BusinessException("An Employee with this email already exists", HttpStatus.CONFLICT);

    }

    private void checkMobileNumberUniquenessOnSave(String mobileNumber) throws BusinessException {
        Employee employeeWithMobileNumber = employeeRepository.findByMobileNumberAndIsDeletedFalse(mobileNumber);
        if (!isNull(employeeWithMobileNumber))
            throw new BusinessException("An Employee with this mobile number already exists", HttpStatus.CONFLICT);

    }

    private void checkUsernameUniquenessOnSave(String username) throws BusinessException {
        Employee employee = employeeRepository.findByUsernameAndIsDeletedFalse(username);
        if (!isNull(employee))
            throw new BusinessException("Username is already taken.", HttpStatus.CONFLICT);
    }

    private void checkNationalIdUniquenessOnSave(String nationalId) throws BusinessException {
        Employee employeeWithNationalId = employeeRepository.findByNationalIdAndIsDeletedFalse(nationalId);
        if (!isNull(employeeWithNationalId))
            throw new BusinessException("An Employee with this national id already exists", HttpStatus.CONFLICT);
    }

    @Override
    public PaginationDTO<ListEmployeeDTO> getAllEmployees(Map<String, String> search, Pageable pageable) {
        EmployeePredicateBuilder builder = new EmployeePredicateBuilder();
        extractQueryParameters(builder, search);

        Page<Employee> employees = employeeRepository.findAll(builder.build(), pageable);

        List<Employee> employeeList = employees.getContent();
        List<ListEmployeeDTO> content = employeeMapper.mapEmployeeEntitiesToListEmployeeDTOs(employeeList);
        return createPageResponse(content, pageable, employees.getTotalElements(), employees.getTotalPages());
    }

    @Override
    public GetEmployeeDTO getEmployeeById(Long id) throws BusinessException {
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(id);
        return employeeMapper.mapEmployeeEntityToGetEmployeeDTO(employee);
    }

    @Override
    public void updateEmployee(Long id, UpdateEmployeeDTO employeeDTO) throws BusinessException {
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(id);

        String newEmail = employeeDTO.getEmail();
        if (!isNull(newEmail)) {
            checkEmailUniquenessOnUpdate(newEmail, id);
        }

        String newMobileNumber = employeeDTO.getMobileNumber();
        if (!isNull(newMobileNumber)) {
            checkMobileNumberUniquenessOnUpdate(newMobileNumber, id);
        }

        String newUsername = employeeDTO.getUsername();
        if (!isNull(newUsername)) {
            checkUsernameUniquenessOnUpdate(newUsername, id);
        }

        String nationalId = employeeDTO.getNationalId();
        if (!isNull(nationalId)) {
            checkNationalIdUniquenessOnUpdate(nationalId, id);
        }

        String newPassword = employeeDTO.getPassword();
        if (!isNull(newPassword)) {
            employeeDTO.setPassword(encoder.encode(newPassword));
        }

        String newDepartment = employeeDTO.getDepartment();
        if (!isNull(newDepartment)) {
            Department department = departmentServiceImpl.getDepartmentWithName(newDepartment);
            employee.setDepartment(department);
        }
        Employee updatedEmployee = employeeMapper.mapUpdatedEmployeeDTOtoEntityDTO(employeeDTO);

        employeeMapper.copyFields(updatedEmployee, employee);
        employeeRepository.save(employee);
    }


    private void checkEmailUniquenessOnUpdate(String email, Long id) throws BusinessException {
        Employee employeeWithEmail = employeeRepository.findByEmailAndIsDeletedFalseAndIdIsNot(email, id);
        if (!isNull(employeeWithEmail))
            throw new BusinessException("An Employee with this email already exists", HttpStatus.CONFLICT);

    }

    private void checkMobileNumberUniquenessOnUpdate(String mobileNumber, Long id) throws BusinessException {
        Employee employeeWithMobileNumber = employeeRepository.findByMobileNumberAndIsDeletedFalseAndIdIsNot(mobileNumber, id);
        if (!isNull(employeeWithMobileNumber))
            throw new BusinessException("An Employee with this mobile number already exists", HttpStatus.CONFLICT);

    }

    private void checkUsernameUniquenessOnUpdate(String username, Long id) throws BusinessException {
        Employee employee = employeeRepository.findByUsernameAndIsDeletedFalseAndIdIsNot(username, id);
        if (!isNull(employee))
            throw new BusinessException("Username is already taken.", HttpStatus.CONFLICT);
    }

    private void checkNationalIdUniquenessOnUpdate(String nationalId, Long id) throws BusinessException {
        Employee employeeWithNationalId = employeeRepository.findByNationalIdAndIsDeletedFalseAndIdIsNot(nationalId, id);
        if (!isNull(employeeWithNationalId))
            throw new BusinessException("An Employee with this national id already exists", HttpStatus.CONFLICT);
    }

    @Override
    public void deleteEmployee(Long id) throws BusinessException {
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }

    private void extractQueryParameters(EmployeePredicateBuilder builder, Map<String, String> search) {
        if (search != null) {
            for (Map.Entry<String, String> entry : search.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (isPageableKey(key)) continue;
                builder.with(key, "=", value);
            }
        }
        builder.with("isDeleted", "=", "false");
    }

}
