package org.project.onboarding.interfaces;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.task.*;
import org.project.onboarding.entity.Task;
import org.project.onboarding.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TaskService {
    GetTaskDTO assignTaskToTeamMember(Long taskId, Long memberId) throws BusinessException;

    void saveTask(PostTaskDTO dto) throws BusinessException;

    PaginationDTO<ListTaskDTO> getAllTasks(Map<String, String> search, Pageable pageable);

    GetTaskDTO getTaskById(Long id) throws BusinessException;

    void updateTask(Long id, UpdateTaskDTO dto) throws BusinessException;

    void deleteTask(Long id) throws BusinessException;

    Task getEntityWithId(Long taskId) throws BusinessException;
}
