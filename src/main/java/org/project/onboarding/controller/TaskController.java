package org.project.onboarding.controller;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.task.GetTaskDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.task.PostTaskDTO;
import org.project.onboarding.dto.task.UpdateTaskDTO;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> saveTask(@RequestBody PostTaskDTO task) throws BusinessException {
        taskService.saveTask(task);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListTaskDTO>> getAllTasks(Map<String, String> search, Pageable pageable) {
        return ResponseEntity.ok(taskService.getAllTasks(search, pageable));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<GetTaskDTO> getTaskById(@PathVariable Long taskId) throws BusinessException {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskDTO task) throws BusinessException {
        taskService.updateTask(taskId, task);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) throws BusinessException {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{taskId}/assign-task/{memberId}")
    public ResponseEntity<GetTaskDTO> assignTaskToTeamMember(@PathVariable Long memberId, @PathVariable Long taskId) throws BusinessException {
        return ResponseEntity.ok(taskService.assignTaskToTeamMember(taskId, memberId));
    }

}
