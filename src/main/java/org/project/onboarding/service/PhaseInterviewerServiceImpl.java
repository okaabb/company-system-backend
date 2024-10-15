package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.application.ListApplicationDTO;
import org.project.onboarding.dto.hiringPhase.PostHiringPhaseDTO;
import org.project.onboarding.dto.phaseInterviewer.*;
import org.project.onboarding.entity.Application;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.HiringPhase;
import org.project.onboarding.entity.PhaseInterviewer;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.PhaseInterviewerService;
import org.project.onboarding.mapper.PhaseInterviewerMapper;
import org.project.onboarding.predicates.Application.ApplicationPredicateBuilder;
import org.project.onboarding.predicates.PhaseInterviewer.PhaseInterviewerPredicateBuilder;
import org.project.onboarding.repository.PhaseInterviewerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.createPageResponse;
import static org.project.onboarding.helperFunctions.PredicatesHelper.isPageableKey;

@Service
public class PhaseInterviewerServiceImpl implements PhaseInterviewerService {
    private final PhaseInterviewerRepository phaseInterviewerRepository;
    private final PhaseInterviewerMapper phaseInterviewerMapper;
    private final HiringPhaseServiceImpl hiringPhaseServiceImpl;
    private final EmployeeBusinessLayer employeeBusinessLayer;

    @Autowired
    public PhaseInterviewerServiceImpl(PhaseInterviewerRepository phaseInterviewerRepository, PhaseInterviewerMapper phaseInterviewerMapper, HiringPhaseServiceImpl hiringPhaseServiceImpl, EmployeeBusinessLayer employeeBusinessLayer) {
        this.phaseInterviewerRepository = phaseInterviewerRepository;
        this.phaseInterviewerMapper = phaseInterviewerMapper;
        this.hiringPhaseServiceImpl = hiringPhaseServiceImpl;
        this.employeeBusinessLayer = employeeBusinessLayer;
    }

    @Override
    public void savePhaseInterviewer(PostPhaseInterviewerDTO dto) throws BusinessException {
        PhaseInterviewer phaseInterviewer = phaseInterviewerMapper.mapPostPhaseInterviewerDTOtoPhaseInterviewerEntity(dto);

        HiringPhase hiringPhase = hiringPhaseServiceImpl.getEntityWithId(dto.getHiringPhaseId());
        Employee interviewer = employeeBusinessLayer.getEmployeeEntityById(dto.getInterviewerId());

        phaseInterviewer.setHiringPhase(hiringPhase);
        phaseInterviewer.setInterviewer(interviewer);

        phaseInterviewerRepository.save(phaseInterviewer);
    }

    @Override
    public PaginationDTO<ListPhaseInterviewerDTO> getAllPhaseInterviewers(Pageable pageable, Map<String, String> search) {
        PhaseInterviewerPredicateBuilder builder = new PhaseInterviewerPredicateBuilder();
        extractQueryParameters(builder, search);
        Page<PhaseInterviewer> phaseInterviewers = phaseInterviewerRepository.findAll(builder.build(), pageable);

        List<PhaseInterviewer> phaseInterviewerList = phaseInterviewers.getContent();
        List<ListPhaseInterviewerDTO> content = phaseInterviewerMapper.mapPhaseInterviewerEntitiesToListPhaseInterviewerDTOs(phaseInterviewerList);
        return createPageResponse(content, pageable, phaseInterviewers.getTotalElements(), phaseInterviewers.getTotalPages());
    }

    @Override
    public GetPhaseInterviewerDTO getPhaseInterviewerById(Long id) throws BusinessException {
        return phaseInterviewerMapper.mapPhaseInterviewerEntityToGetPhaseInterviewerDTO(getEntityWithId(id));
    }

    @Override
    public void updatePhaseInterviewer(Long id, UpdatePhaseInterviewerDTO dto) throws BusinessException {
        PhaseInterviewer phaseInterviewer = getEntityWithId(id);
        PhaseInterviewer updatedPhaseInterviewer = phaseInterviewerMapper.mapUpdatePhaseInterviewerDTOtoPhaseInterviewerEntity(dto);

        HiringPhase hiringPhase = hiringPhaseServiceImpl.getEntityWithId(dto.getHiringPhaseId());
        Employee interviewer = employeeBusinessLayer.getEmployeeEntityById(dto.getInterviewerId());

        phaseInterviewer.setHiringPhase(hiringPhase);
        phaseInterviewer.setInterviewer(interviewer);

        phaseInterviewerMapper.copyFields(updatedPhaseInterviewer, phaseInterviewer);
        phaseInterviewerRepository.save(phaseInterviewer);
    }

    @Override
    public void deletePhaseInterviewer(Long id) throws BusinessException {
        PhaseInterviewer phaseInterviewer = getEntityWithId(id);
        phaseInterviewer.setIsDeleted(true);
        phaseInterviewerRepository.save(phaseInterviewer);
    }

    @Override
    public PhaseInterviewer getEntityWithId(Long phaseInterviewerId) throws BusinessException {
        PhaseInterviewer phaseInterviewer = phaseInterviewerRepository.findByIdAndIsDeletedFalse(phaseInterviewerId);
        if (isNull(phaseInterviewer))
            throw new BusinessException("Phase Interviewer does not exist.", HttpStatus.NOT_FOUND);
        return phaseInterviewer;
    }

    public static void extractQueryParameters(PhaseInterviewerPredicateBuilder builder, Map<String, String> search) {
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
