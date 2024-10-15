package org.project.onboarding.dto.employeeTraining;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListEmployeeTrainingDTO {
    private Long trainingId;
    private Long employeeId;
}
