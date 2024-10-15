package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.application.GetApplicationDTO;
import org.project.onboarding.dto.application.ListApplicationDTO;
import org.project.onboarding.dto.application.PostApplicationDTO;
import org.project.onboarding.dto.application.UpdateApplicationDTO;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.entity.Application;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.enums.ApplicationStatusEnum;
import org.project.onboarding.enums.PositionEnum;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.ApplicationService;
import org.project.onboarding.mapper.ApplicationMapper;
import org.project.onboarding.predicates.Application.ApplicationPredicateBuilder;
import org.project.onboarding.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.createPageResponse;
import static org.project.onboarding.helperFunctions.PredicatesHelper.isPageableKey;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final EmployeeBusinessLayer employeeBusinessLayer;
    private final DepartmentServiceImpl departmentService;
    private final ApplicantServiceImpl applicantService;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, EmployeeBusinessLayer employeeBusinessLayer, DepartmentServiceImpl departmentService, ApplicantServiceImpl applicantService) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.employeeBusinessLayer = employeeBusinessLayer;
        this.departmentService = departmentService;
        this.applicantService = applicantService;
    }

    @Override
    public void saveApplication(PostApplicationDTO dto) throws BusinessException {
        Application application = applicationMapper.mapPostApplicationToApplicationEntity(dto);

        Long applicantId = dto.getApplicantId();
        Applicant applicant = checkApplicantExistence(applicantId);
        application.setApplicant(applicant);

        Long hrId = dto.getHrId();
        Employee hr = checkHRExistence(hrId);
        application.setHr(hr);

        String departmentName = dto.getDepartmentName();
        Department department = checkDepartmentExistence(departmentName);
        application.setDepartment(department);

        Long referralEmployeeId = dto.getReferralEmployeeId();
        if (!isNull(referralEmployeeId)) {
            Employee referralEmployee = checkReferralEmployeeExistence(referralEmployeeId);
            application.setReferralEmployee(referralEmployee);
        }

        applicationRepository.save(application);
    }

    @Override
    public PaginationDTO<ListApplicationDTO> getAllApplications(Pageable pageable, Map<String, String> search) {
        ApplicationPredicateBuilder builder = new ApplicationPredicateBuilder();
        extractQueryParameters(builder, search);
        Page<Application> applications = applicationRepository.findAll(builder.build(), pageable);

        List<Application> applicationList = applications.getContent();
        List<ListApplicationDTO> content = applicationMapper.mapApplicationEntitiesToListApplicationDTOs(applicationList);
        return createPageResponse(content, pageable, applications.getTotalElements(), applications.getTotalPages());
    }

    @Override
    public GetApplicationDTO getApplicationById(Long id) throws BusinessException {
        Application application = getEntityWithId(id);
        return applicationMapper.mapApplicationEntityToApplicationGet(application);
    }

    @Override
    public void updateApplication(Long id, UpdateApplicationDTO dto) throws BusinessException {
        Application application = getEntityWithId(id);
        Application updateApplication = applicationMapper.mapUpdateApplicationDTOtoApplicationEntityDTO(dto);

        Long hrId = dto.getHrId();
        if (!isNull(hrId)) {
            Employee hr = checkHRExistence(hrId);
            application.setHr(hr);
        }

        String departmentName = dto.getDepartmentName();
        if (!isNull(departmentName)) {
            Department department = checkDepartmentExistence(departmentName);
            application.setDepartment(department);
        }

        Long referralEmployeeId = dto.getReferralEmployeeId();
        if (!isNull(referralEmployeeId)) {
            Employee referralEmployee = checkReferralEmployeeExistence(referralEmployeeId);
            application.setReferralEmployee(referralEmployee);
        }
        applicationMapper.copyFields(updateApplication, application);
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long id) throws BusinessException {
        Application application = getEntityWithId(id);
        application.setIsDeleted(true);
        applicationRepository.save(application);
    }

    public Application getEntityWithId(Long id) throws BusinessException {
        Application application = applicationRepository.findByIdAndIsDeletedFalse(id);
        if (isNull(application))
            throw new BusinessException("Application does not exist.", HttpStatus.NOT_FOUND);
        return application;
    }

    private Applicant checkApplicantExistence(Long id) throws BusinessException {
        return applicantService.getEntityWithId(id);
    }

    private Employee checkHRExistence(Long id) throws BusinessException {
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(id);
        if (employee.getPosition() != PositionEnum.HR)
            throw new BusinessException("Employee entered in HR field is not an HR.", HttpStatus.BAD_REQUEST);
        return employee;
    }

    private Employee checkReferralEmployeeExistence(Long id) throws BusinessException {
        return employeeBusinessLayer.getEmployeeEntityById(id);
    }

    private Department checkDepartmentExistence(String name) throws BusinessException {
        return departmentService.getDepartmentWithName(name);
    }

    public static void extractQueryParameters(ApplicationPredicateBuilder builder, Map<String, String> search) {
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