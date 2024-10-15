package org.project.onboarding.dto.employeeTraining;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeTrainingDTO {
    private Long trainingId;
    private Long employeeId;
    private LocalDate enrollmentDate;
}
