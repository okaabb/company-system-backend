package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.sprint.GetSlimSprintDTO;
import org.project.onboarding.dto.task.GetTaskDTO;
import org.project.onboarding.dto.task.ListTaskDTO;
import org.project.onboarding.dto.task.PostTaskDTO;
import org.project.onboarding.dto.task.UpdateTaskDTO;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.entity.Sprint;
import org.project.onboarding.entity.Task;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task mapPostTaskDTOtoTaskEntity(PostTaskDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.name( dto.getName() );
        task.description( dto.getDescription() );
        task.duration( dto.getDuration() );

        return task.build();
    }

    @Override
    public Task mapUpdateTaskDTOtoTaskEntity(UpdateTaskDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.name( dto.getName() );
        task.description( dto.getDescription() );
        task.duration( dto.getDuration() );

        return task.build();
    }

    @Override
    public GetTaskDTO mapTaskEntityToGetTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        GetTaskDTO.GetTaskDTOBuilder getTaskDTO = GetTaskDTO.builder();

        getTaskDTO.name( task.getName() );
        getTaskDTO.sprint( sprintToGetSlimSprintDTO( task.getSprint() ) );
        getTaskDTO.employee( employeeToEmployeeDTO( task.getEmployee() ) );
        getTaskDTO.description( task.getDescription() );
        getTaskDTO.assignedBy( employeeToEmployeeDTO( task.getAssignedBy() ) );
        getTaskDTO.duration( task.getDuration() );

        return getTaskDTO.build();
    }

    @Override
    public List<ListTaskDTO> mapTaskEntitiesToListTaskDTOs(List<Task> taskList) {
        if ( taskList == null ) {
            return null;
        }

        List<ListTaskDTO> list = new ArrayList<ListTaskDTO>( taskList.size() );
        for ( Task task : taskList ) {
            list.add( taskToListTaskDTO( task ) );
        }

        return list;
    }

    @Override
    public void copyFields(Task updatedTask, Task task) {
        if ( updatedTask == null ) {
            return;
        }

        if ( updatedTask.getId() != null ) {
            task.setId( updatedTask.getId() );
        }
        if ( updatedTask.getName() != null ) {
            task.setName( updatedTask.getName() );
        }
        if ( updatedTask.getDescription() != null ) {
            task.setDescription( updatedTask.getDescription() );
        }
        if ( updatedTask.getSprint() != null ) {
            task.setSprint( updatedTask.getSprint() );
        }
        if ( updatedTask.getEmployee() != null ) {
            task.setEmployee( updatedTask.getEmployee() );
        }
        if ( updatedTask.getAssignedBy() != null ) {
            task.setAssignedBy( updatedTask.getAssignedBy() );
        }
        if ( updatedTask.getDuration() != null ) {
            task.setDuration( updatedTask.getDuration() );
        }
        if ( updatedTask.getUpdatedAt() != null ) {
            task.setUpdatedAt( updatedTask.getUpdatedAt() );
        }
        if ( updatedTask.getUpdatedBy() != null ) {
            task.setUpdatedBy( updatedTask.getUpdatedBy() );
        }
        if ( updatedTask.getIsDeleted() != null ) {
            task.setIsDeleted( updatedTask.getIsDeleted() );
        }
    }

    protected GetSlimSprintDTO sprintToGetSlimSprintDTO(Sprint sprint) {
        if ( sprint == null ) {
            return null;
        }

        GetSlimSprintDTO.GetSlimSprintDTOBuilder getSlimSprintDTO = GetSlimSprintDTO.builder();

        getSlimSprintDTO.id( sprint.getId() );
        getSlimSprintDTO.name( sprint.getName() );

        return getSlimSprintDTO.build();
    }

    protected EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder<?, ?> employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( employee.getId() );
        employeeDTO.name( employee.getName() );
        employeeDTO.email( employee.getEmail() );

        return employeeDTO.build();
    }

    protected ListTaskDTO taskToListTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        ListTaskDTO.ListTaskDTOBuilder listTaskDTO = ListTaskDTO.builder();

        listTaskDTO.id( task.getId() );
        listTaskDTO.name( task.getName() );

        return listTaskDTO.build();
    }
}
