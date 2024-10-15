package org.project.onboarding.dto.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.employee.EmployeeDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListTrainingDTO {
    private Long id;
    private String name;
    private EmployeeDTO creator;
    private Float trainingHours;
}
