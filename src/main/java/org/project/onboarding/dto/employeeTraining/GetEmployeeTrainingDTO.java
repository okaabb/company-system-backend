package org.project.onboarding.dto.employeeTraining;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.training.GetTrainingDTO;
import org.project.onboarding.dto.training.TrainingLookUpDTO;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeTrainingDTO {
    private Long id;
    private TrainingLookUpDTO training;
    private EmployeeDTO employee;
    private LocalDate enrollmentDate;
}
