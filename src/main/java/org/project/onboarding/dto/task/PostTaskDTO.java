package org.project.onboarding.dto.task;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTaskDTO{
    @NotNull(message = "Task must have a name.")
    private String name;

    @NotNull(message = "Task must belong to a sprint.")
    private Long sprintId;

    private Long employeeId;

    @NotNull(message = "Task must have a description.")
    private String description;

    private Long assignedById;

    @NotNull(message = "Task must have a duration.")
    private Float duration;
}
