package org.project.onboarding.service;

import org.project.onboarding.businessLayers.EmployeeBusinessLayer;
import org.project.onboarding.businessLayers.TeamBusinessLayer;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.task.GetTaskDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.task.PostTaskDTO;
import org.project.onboarding.dto.task.UpdateTaskDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Sprint;
import org.project.onboarding.entity.Task;
import org.project.onboarding.entity.Team;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.TaskService;
import org.project.onboarding.mapper.TaskMapper;
import org.project.onboarding.predicates.Task.TaskPredicateBuilder;
import org.project.onboarding.repository.TaskRepository;
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
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TeamBusinessLayer teamBusinessLayer;
    private final EmployeeBusinessLayer employeeBusinessLayer;
    private final TaskMapper taskMapper;
    private final SprintServiceImpl sprintService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TeamBusinessLayer teamBusinessLayer, EmployeeBusinessLayer employeeBusinessLayer, TaskMapper taskMapper, SprintServiceImpl sprintService) {
        this.taskRepository = taskRepository;
        this.teamBusinessLayer = teamBusinessLayer;
        this.employeeBusinessLayer = employeeBusinessLayer;
        this.taskMapper = taskMapper;
        this.sprintService = sprintService;
    }

    @Override
    public void saveTask(PostTaskDTO dto) throws BusinessException {
        Task task = taskMapper.mapPostTaskDTOtoTaskEntity(dto);
        Sprint sprint = sprintService.getEntityWithId(dto.getSprintId());
        Employee assignedBy = employeeBusinessLayer.getEmployeeEntityById(dto.getAssignedById());
        if (!isNull(dto.getEmployeeId())) {
            Employee employee = employeeBusinessLayer.getEmployeeEntityById(dto.getEmployeeId());
            task.setEmployee(employee);
        }
        task.setSprint(sprint);
        task.setAssignedBy(assignedBy);
        taskRepository.save(task);
    }

    @Override
    public PaginationDTO<ListTaskDTO> getAllTasks(Map<String, String> search, Pageable pageable) {
        TaskPredicateBuilder builder = new TaskPredicateBuilder();
        extractQueryParameters(builder, search);

        Page<Task> tasks = taskRepository.findAll(builder.build(), pageable);

        List<Task> taskList = tasks.getContent();
        List<ListTaskDTO> content = taskMapper.mapTaskEntitiesToListTaskDTOs(taskList);
        return createPageResponse(content, pageable, tasks.getTotalElements(), tasks.getTotalPages());
    }

    @Override
    public GetTaskDTO getTaskById(Long id) throws BusinessException {
        Task task = getEntityWithId(id);
        return taskMapper.mapTaskEntityToGetTaskDTO(task);
    }

    @Override
    public void updateTask(Long id, UpdateTaskDTO dto) throws BusinessException {
        Task task = getEntityWithId(id);
        Task updatedTask = taskMapper.mapUpdateTaskDTOtoTaskEntity(dto);
        Sprint sprint = sprintService.getEntityWithId(dto.getSprintId());
        Employee assignedBy = employeeBusinessLayer.getEmployeeEntityById(dto.getAssignedById());
        if (!isNull(dto.getEmployeeId())) {
            Employee employee = employeeBusinessLayer.getEmployeeEntityById(dto.getEmployeeId());
            updatedTask.setEmployee(employee);
        }
        updatedTask.setSprint(sprint);
        updatedTask.setAssignedBy(assignedBy);
        taskMapper.copyFields(updatedTask, task);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) throws BusinessException {
        Task task = getEntityWithId(id);
        task.setIsDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public Task getEntityWithId(Long taskId) throws BusinessException {
        Task task = taskRepository.findByIdAndIsDeletedFalse(taskId);
        if (isNull(task)) throw new BusinessException("Task does not exist.", HttpStatus.NOT_FOUND);
        return task;
    }


    @Override
    public GetTaskDTO assignTaskToTeamMember(Long taskId, Long memberId) throws BusinessException {
        Employee employee = employeeBusinessLayer.getEmployeeEntityById(memberId);
        Task task = getEntityWithId(taskId);
        task.setEmployee(employee);
        taskRepository.save(task);
        return taskMapper.mapTaskEntityToGetTaskDTO(task);
    }

    public static void extractQueryParameters(TaskPredicateBuilder builder, Map<String, String> search) {
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
