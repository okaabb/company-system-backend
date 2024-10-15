package org.project.onboarding.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDTO {
    private String name;
    private Long sprintId;
    private Long employeeId;
    private String description;
    private Long assignedById;
    private Float duration;
}
