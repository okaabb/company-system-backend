package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.phaseInterviewer.*;
import org.project.onboarding.entity.PhaseInterviewer;
import org.project.onboarding.entity.PhaseInterviewer;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PhaseInterviewerService {

    PhaseInterviewer getEntityWithId(Long phaseInterviewerId) throws BusinessException;

    void deletePhaseInterviewer(Long id) throws BusinessException;

    void updatePhaseInterviewer(Long id, UpdatePhaseInterviewerDTO dto) throws BusinessException;

    GetPhaseInterviewerDTO getPhaseInterviewerById(Long id) throws BusinessException;

    PaginationDTO<ListPhaseInterviewerDTO> getAllPhaseInterviewers(Pageable pageable, Map<String, String> search);

    void savePhaseInterviewer(PostPhaseInterviewerDTO dto) throws BusinessException;

}
