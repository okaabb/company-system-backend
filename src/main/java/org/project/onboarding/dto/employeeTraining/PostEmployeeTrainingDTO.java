package org.project.onboarding.dto.employeeTraining;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostEmployeeTrainingDTO {
    @NotNull(message = "Training Id can not be empty.")
    private Long trainingId;

    @NotNull(message = "Employee Id can not be empty.")
    private Long employeeId;

    @NotNull(message = "Must have an enrollment date.")
    private LocalDate enrollmentDate;
}
