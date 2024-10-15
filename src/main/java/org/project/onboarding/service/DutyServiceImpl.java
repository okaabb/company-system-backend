package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.duty.GetDutyDTO;
import org.project.onboarding.dto.duty.ListDutyDTO;
import org.project.onboarding.dto.duty.PostDutyDTO;
import org.project.onboarding.dto.duty.UpdateDutyDTO;
import org.project.onboarding.entity.Duty;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.enums.DutyStatusEnum;
import org.project.onboarding.enums.PositionEnum;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.DutyService;
import org.project.onboarding.mapper.DutyMapper;
import org.project.onboarding.predicates.Duty.DutyPredicateBuilder;
import org.project.onboarding.repository.DutyRepository;
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
public class DutyServiceImpl implements DutyService {
    private final DutyRepository dutyRepository;
    private final DutyMapper dutyMapper;
    private final EmployeeBusinessLayer employeeBusinessLayer;

    @Autowired
    public DutyServiceImpl(DutyRepository dutyRepository, DutyMapper dutyMapper, EmployeeBusinessLayer employeeBusinessLayer) {
        this.dutyRepository = dutyRepository;
        this.dutyMapper = dutyMapper;
        this.employeeBusinessLayer = employeeBusinessLayer;
    }

    @Override
    public void saveDuty(PostDutyDTO dto) throws BusinessException {
        Duty duty = dutyMapper.mapPostDutyDTOtoDutyEntityDTO(dto);

        Long employeeId = dto.getEmployeeId();
        Employee employee = checkEmployeeExistence(employeeId);
        duty.setEmployee(employee);

        duty.setStatus(DutyStatusEnum.PENDING);
        dutyRepository.save(duty);
    }

    public PaginationDTO<ListDutyDTO> getAllDuties(Pageable pageable, Map<String, String> search) {
        DutyPredicateBuilder builder = new DutyPredicateBuilder();
        extractQueryParameters(builder, search);
        Page<Duty> duties = dutyRepository.findAll(builder.build(), pageable);

        List<Duty> dutyList = duties.getContent();
        List<ListDutyDTO> content = dutyMapper.mapDutyEntitiesToListDutyDTOs(dutyList);
        return createPageResponse(content, pageable, duties.getTotalElements(), duties.getTotalPages());
    }

    @Override
    public GetDutyDTO getDutyById(Long id) throws BusinessException {
        return dutyMapper.mapDutyEntityToGetDutyDTO(getEntityWithId(id));
    }

    @Override
    public void updateDuty(Long id, UpdateDutyDTO dto) throws BusinessException {
        Duty duty = getEntityWithId(id);
        Duty updatedDuty = dutyMapper.mapUpdateDutyDTOtoDutyEntityDTO(dto);
        dutyMapper.copyFields(updatedDuty, duty);
        dutyRepository.save(duty);
    }

    @Override
    public void deleteDuty(Long id) throws BusinessException {
        Duty duty = getEntityWithId(id);
        duty.setIsDeleted(true);
        dutyRepository.save(duty);
    }

    public Duty getEntityWithId(Long id) throws BusinessException {
        Duty duty = dutyRepository.findByIdAndIsDeletedFalse(id);
        if (isNull(duty))
            throw new BusinessException("Duty does not exist.", HttpStatus.NOT_FOUND);
        return duty;
    }


    @Override
    public void approveDuty(Long id, Long approvedById) throws BusinessException {
        Duty duty = getEntityWithId(id);
        Employee approvedBy = employeeBusinessLayer.getEmployeeEntityById(approvedById);
//       checkApprovingEmployeePosition(approvedBy.getPosition());
//       ha3melha bl roles fl authorization yeba TL, PO, Manager only
        duty.setStatus(DutyStatusEnum.ACCEPTED);
        duty.setApprovedByEmployee(approvedBy);
    }

    @Override
    public void rejectDuty(Long id, Long rejctedById) throws BusinessException {
        Duty duty = getEntityWithId(id);
        Employee approvedBy = employeeBusinessLayer.getEmployeeEntityById(rejctedById);
//       checkApprovingEmployeePosition(approvedBy.getPosition());
//       ha3melha bl roles fl authorization yeba TL, PO, Manager only
        duty.setStatus(DutyStatusEnum.REJECTED);
        duty.setApprovedByEmployee(approvedBy);
    }

    private Employee checkEmployeeExistence(Long id) throws BusinessException {
        return employeeBusinessLayer.getEmployeeEntityById(id);
    }

    public static void extractQueryParameters(DutyPredicateBuilder builder, Map<String, String> search) {
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
