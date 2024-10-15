package org.project.onboarding.dto.sprint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.employee.EmployeeDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListSprintDTO {
    private Long id;
    private String name;
    private EmployeeDTO creator;
}
