package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.task.GetTaskDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.task.PostTaskDTO;
import org.project.onboarding.dto.task.UpdateTaskDTO;
import org.project.onboarding.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    @Mapping(target = "sprint", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "assignedBy", ignore = true)
    Task mapPostTaskDTOtoTaskEntity(PostTaskDTO dto);

    @Mapping(target = "sprint", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "assignedBy", ignore = true)
    Task mapUpdateTaskDTOtoTaskEntity(UpdateTaskDTO dto);

    GetTaskDTO mapTaskEntityToGetTaskDTO(Task task);

    List<ListTaskDTO> mapTaskEntitiesToListTaskDTOs(List<Task> taskList);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(Task updatedTask, @MappingTarget Task task);

}
