package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.application.*;
import org.project.onboarding.entity.Application;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
    void saveApplication(PostApplicationDTO dto) throws BusinessException;

    PaginationDTO<ListApplicationDTO> getAllApplications(Pageable pageable, Map<String, String> search);

    GetApplicationDTO getApplicationById(Long id) throws BusinessException;

    void updateApplication(Long id, UpdateApplicationDTO dto) throws BusinessException;

    void deleteApplication(Long id) throws BusinessException;
}