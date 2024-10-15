package org.project.onboarding.dto.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.employee.EmployeeDTO;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetTrainingDTO {
    private String name;
    private EmployeeDTO creator;
    private LocalDate createdDate;
    private Float trainingHours;
}
