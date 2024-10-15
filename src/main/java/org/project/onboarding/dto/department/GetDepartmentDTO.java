package org.project.onboarding.dto.department;

import lombok.*;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.employee.EmployeePositionDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDepartmentDTO {
    private String name;
    private EmployeeDTO manager;
    private List<EmployeePositionDTO> employees;
}
