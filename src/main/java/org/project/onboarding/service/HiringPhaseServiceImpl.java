package org.project.onboarding.service;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.hiringPhase.GetHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.ListHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.PostHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.UpdateHiringPhase;
import org.project.onboarding.entity.Application;
import org.project.onboarding.entity.HiringPhase;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.HiringPhaseService;
import org.project.onboarding.mapper.HiringPhaseMapper;
import org.project.onboarding.predicates.HiringPhase.HiringPhasePredicateBuilder;
import org.project.onboarding.repository.HiringPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.createPageResponse;
import static org.project.onboarding.helperFunctions.PredicatesHelper.isPageableKey;

@Service
public class HiringPhaseServiceImpl implements HiringPhaseService {
    private final HiringPhaseRepository hiringPhaseRepository;
    private final HiringPhaseMapper hiringPhaseMapper;
    private final ApplicationServiceImpl applicationServiceImpl;

    @Autowired
    public HiringPhaseServiceImpl(HiringPhaseRepository hiringPhaseRepository, HiringPhaseMapper hiringPhaseMapper, ApplicationServiceImpl applicationServiceImpl) {
        this.hiringPhaseRepository = hiringPhaseRepository;
        this.hiringPhaseMapper = hiringPhaseMapper;
        this.applicationServiceImpl = applicationServiceImpl;
    }

    @Override
    public void saveHiringPhase(PostHiringPhaseDTO dto) throws BusinessException {
        Application application = checkApplicationIdExistence(dto.getApplicationId());
        HiringPhase hiringPhase = hiringPhaseMapper.mapHiringPhasePostDTOtoHiringPhaseEntity(dto);
        hiringPhase.setApplication(application);
        hiringPhaseRepository.save(hiringPhase);
    }

    @Override
    public PaginationDTO<ListHiringPhaseDTO> getAllHiringPhases(Pageable pageable, Map<String, String> search) {
        HiringPhasePredicateBuilder builder = new HiringPhasePredicateBuilder();
        extractQueryParameters(builder, search);

        Page<HiringPhase> hiringPhases = hiringPhaseRepository.findAll(builder.build(), pageable);

        List<HiringPhase> hiringPhaseList = hiringPhases.getContent();
        List<ListHiringPhaseDTO> content = hiringPhaseMapper.mapHiringPhaseEntitiesToListHiringPhaseDTOs(hiringPhaseList);
        return createPageResponse(content, pageable, hiringPhases.getTotalElements(), hiringPhases.getTotalPages());
    }

    @Override
    public GetHiringPhaseDTO getHiringPhaseById(Long id) throws BusinessException {
        HiringPhase hiringPhase = getEntityWithId(id);
        return hiringPhaseMapper.mapHiringPhaseEntityToHiringPhaseGetDTO(hiringPhase);
    }

    @Override
    public void updateHiringPhase(Long id, UpdateHiringPhase dto) throws BusinessException {
        HiringPhase hiringPhase = getEntityWithId(id);
        HiringPhase updatedHiringPhase = hiringPhaseMapper.mapHiringPhaseUpdateDTOtoHiringPhaseEntity(dto);
        hiringPhaseMapper.copyFields(updatedHiringPhase, hiringPhase);
        hiringPhaseRepository.save(hiringPhase);
    }

    @Override
    public void deleteHiringPhase(Long id) throws BusinessException {
        HiringPhase hiringPhase = getEntityWithId(id);
        hiringPhase.setIsDeleted(true);
        hiringPhaseRepository.save(hiringPhase);
    }

    @Override
    public HiringPhase getEntityWithId(Long hiringPhaseId) throws BusinessException {
        HiringPhase hiringPhase = hiringPhaseRepository.findByIdAndIsDeletedFalse(hiringPhaseId);
        if (isNull(hiringPhase)) throw new BusinessException("HiringPhase Does not exist.", HttpStatus.NOT_FOUND);
        return hiringPhase;
    }


    public static void extractQueryParameters(HiringPhasePredicateBuilder builder, Map<String, String> search) {
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

    private Application checkApplicationIdExistence(Long applicationId) throws BusinessException {
        return applicationServiceImpl.getEntityWithId(applicationId);
    }

}