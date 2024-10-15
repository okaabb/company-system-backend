package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.hiringPhase.GetHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.ListHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.PostHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.UpdateHiringPhase;
import org.project.onboarding.entity.HiringPhase;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface HiringPhaseService {

    void deleteHiringPhase(Long id) throws BusinessException;

    void updateHiringPhase(Long id, UpdateHiringPhase dto) throws BusinessException;

    HiringPhase getEntityWithId(Long id) throws BusinessException;

    PaginationDTO<ListHiringPhaseDTO> getAllHiringPhases(Pageable pageable, Map<String, String> search);

    void saveHiringPhase(PostHiringPhaseDTO dto) throws BusinessException;

    GetHiringPhaseDTO getHiringPhaseById(Long id) throws BusinessException;

}