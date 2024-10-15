package org.project.onboarding.dto.duty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.enums.DutyTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookUpDutyDTO {
    private Long id;
    private DutyTypeEnum type;
    private EmployeeDTO employeeId;
}
