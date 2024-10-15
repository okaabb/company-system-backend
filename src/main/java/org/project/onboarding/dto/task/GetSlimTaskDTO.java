package org.project.onboarding.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetSlimTaskDTO {
    private Long id;
    private String name;
    private Long sprintId;
    private Long employeeId;
}
