package org.project.onboarding.dto.duty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.enums.DutyStatusEnum;
import org.project.onboarding.enums.DutyTypeEnum;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDutyDTO {
    private DutyTypeEnum type;
    private EmployeeDTO employee;
    private EmployeeDTO approvedByEmployee;
    private LocalDate recordDate;
    private LocalDate dutyDateFrom;
    private LocalDate dutyDateTo;
    private DutyStatusEnum status;
    private Float workingHoursPerDay;
}
