package org.project.onboarding.dto.task;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.sprint.GetSlimSprintDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskDTO  {
    private String name;
    private GetSlimSprintDTO sprint;
    private EmployeeDTO employee;
    private String description;
    private EmployeeDTO assignedBy;
    private Float duration;
}
