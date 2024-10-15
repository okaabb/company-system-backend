package org.project.onboarding.dto.employee;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ListEmployeeDTO extends EmployeePositionDTO {
    private String department;
}
