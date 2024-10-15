package org.project.onboarding.dto.employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.enums.PositionEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePositionDTO extends EmployeeDTO {
    private PositionEnum position;
}
