package org.project.onboarding.dto.duty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.enums.DutyStatusEnum;
import org.project.onboarding.enums.DutyTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListDutyDTO {
    private String employee;
    private DutyTypeEnum type;
    private DutyStatusEnum status;
    private String approvedBy;
    private Float workingHoursPerDay;

}
