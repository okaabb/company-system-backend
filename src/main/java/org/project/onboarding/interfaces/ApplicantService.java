package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.applicant.*;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface ApplicantService {
    void saveApplicant(ApplicantDTO dto) throws BusinessException;

    PaginationDTO<ListApplicantsResponseDTO> getAllApplicants(Map<String, String> search, Pageable pageable);

    GetApplicantResponseDTO getApplicantById(Long id) throws BusinessException;

    void updateApplicant(Long id, UpdateApplicantDTO dto) throws BusinessException;

    void deleteApplicant(Long id) throws BusinessException;

    Applicant getEntityWithId(Long ApplicantId) throws BusinessException;
}