package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.sprint.GetSprintDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.sprint.PostSprintDTO;
import org.project.onboarding.dto.sprint.UpdateSprintDTO;
import org.project.onboarding.entity.Sprint;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface SprintService {
    void saveSprint(PostSprintDTO dto) throws BusinessException;

    PaginationDTO<ListSprintDTO> getAllSprints(Map<String, String> search, Pageable pageable);

    GetSprintDTO getSprintById(Long id) throws BusinessException;

    void updateSprint(Long id, UpdateSprintDTO dto) throws BusinessException;

    void deleteSprint(Long id) throws BusinessException;

    GetSprintDTO assignSprintToTeam(Long id, Long teamId) throws BusinessException;

    Sprint getEntityWithId(Long sprintId) throws BusinessException;
}
