package org.project.onboarding.dto.team;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.dto.employee.EmployeeDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTeamResponseDTO {
    private String name;
    private EmployeeDTO teamLead;
    private DepartmentDTO department;
}
