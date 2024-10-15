package org.project.onboarding.interfaces;

import jakarta.validation.Valid;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.duty.*;
import org.project.onboarding.entity.Duty;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface DutyService {

    void deleteDuty(Long id) throws BusinessException;

    void updateDuty(Long id, UpdateDutyDTO dto) throws BusinessException;

    GetDutyDTO getDutyById(Long id) throws BusinessException;

    PaginationDTO<ListDutyDTO> getAllDuties(Pageable pageable, Map<String, String> search);

    void saveDuty(PostDutyDTO dto) throws BusinessException;

    void approveDuty(Long id, Long approvedById) throws BusinessException;

    void rejectDuty(Long id, Long rejectedById) throws BusinessException;
}
