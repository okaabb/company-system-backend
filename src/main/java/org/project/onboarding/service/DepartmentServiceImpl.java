package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.department.DepartmentPaginationDTO;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.dto.department.ListDepartmentDTO;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.QDepartment;
import org.project.onboarding.enums.PositionEnum;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.DepartmentService;
import org.project.onboarding.mapper.DepartmentMapper;
import org.project.onboarding.predicates.Department.DepartmentPredicateBuilder;
import org.project.onboarding.repository.DepartmentRepository;
import org.project.onboarding.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    final private DepartmentRepository departmentRepository;
    final private DepartmentMapper departmentMapper;
    final private EmployeeBusinessLayer employeeBusinessLayer;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, EmployeeBusinessLayer employeeBusinessLayer) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.employeeBusinessLayer = employeeBusinessLayer;
    }

    @Override
    public void saveDepartment(DepartmentDTO departmentDTO) throws BusinessException {
        Department department = departmentMapper.mapDepartmentPostDTOtoDepartmentEntity(departmentDTO);

        String departmentName = departmentDTO.getName();
        checkNameUniquenessOnSave(departmentName);

        Long managerId = departmentDTO.getManagerId();
        if (!isNull(managerId)) {
            Employee manager = employeeBusinessLayer.getEmployeeEntityById(managerId);
            validateManagerRole(manager);
            department.setManager(manager);
        }
        departmentRepository.save(department);
    }

    @Override
    public DepartmentPaginationDTO getAllDepartments(Map<String, String> search, Pageable pageable) {
        DepartmentPredicateBuilder builder = new DepartmentPredicateBuilder();
        extractQueryParameters(builder, search);

        Page<Department> departments = departmentRepository.findAll(builder.build(), pageable);

        List<Department> departmentList = departments.getContent();
        List<ListDepartmentDTO> content = departmentMapper.mapDepartmentEntitiesToListDepartmentDTOs(departmentList);
        return createPageResponse(content, pageable, departments.getTotalElements(), departments.getTotalPages());
    }

    @Override
    public GetDepartmentDTO getDepartmentById(Long id) throws BusinessException {
        Department department = getDepartmentEntityById(id);
        return departmentMapper.mapDepartmentEntityToGetDepartmentDTO(department);
    }

    @Override
    public void updateDepartment(Long id, DepartmentDTO departmentDTO) throws BusinessException {
        Department existingDepartment = getDepartmentEntityById(id);

        String departmentName = departmentDTO.getName();
        if (!isNull(departmentName)) {
            checkNameUniquenessOnUpdate(departmentName, id);
            existingDepartment.setName(departmentName);
        }

        Long managerId = departmentDTO.getManagerId();
        if (!isNull(managerId)) {
            Employee newManager = employeeBusinessLayer.getEmployeeEntityById(managerId);
            validateManagerRole(newManager);
            existingDepartment.setManager(newManager);
        }

        departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) throws BusinessException {
        Department department = getDepartmentEntityById(id);
        department.setIsDeleted(true);
        departmentRepository.save(department);
    }

    public Department getDepartmentEntityById(Long departmentId) throws BusinessException {
        Department department = departmentRepository.findByIdAndIsDeletedFalse(departmentId);
        if (isNull(department))
            throw new BusinessException("Department does not exist.", HttpStatus.NOT_FOUND);
        return department;
    }

    @Override
    public void checkNameUniquenessOnSave(String departmentName) throws BusinessException {
        Department departmentWithEqName = departmentRepository.findByNameAndIsDeletedFalse(departmentName);
        if (!isNull(departmentWithEqName))
            throw new BusinessException("A department with name already exists.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void checkNameUniquenessOnUpdate(String departmentName, Long id) throws BusinessException {
        Department departmentWithEqName = departmentRepository.findByNameAndIsDeletedFalseAndIdIsNot(departmentName, id);
        if (!isNull(departmentWithEqName))
            throw new BusinessException("A department with name already exists.", HttpStatus.BAD_REQUEST);
    }

    public Department getDepartmentWithName(String departmentName) throws BusinessException {
        Department departmentWithName = departmentRepository.findByNameAndIsDeletedFalse(departmentName);
        if (isNull(departmentWithName))
            throw new BusinessException("No such department exists.", HttpStatus.NOT_FOUND);
        return departmentWithName;
    }

    private void validateManagerRole(Employee manager) throws BusinessException {
        if (manager.getPosition() != PositionEnum.MANAGER)
            throw new BusinessException("Employee with given id is not a manager.", HttpStatus.BAD_REQUEST);
    }

    private DepartmentPaginationDTO createPageResponse(List<ListDepartmentDTO> content, Pageable pageable, long totalElements, int totalPages) {
        DepartmentPaginationDTO response = new DepartmentPaginationDTO();
        response.setContent(content);
        response.setPageNumber(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;

    }

    private void extractQueryParameters(DepartmentPredicateBuilder builder, Map<String, String> search) {
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

    private boolean isPageableKey(String key) {
        Set<String> pageableKeys = Set.of("page", "size", "sort");
        return pageableKeys.contains(key.toLowerCase());
    }
}
