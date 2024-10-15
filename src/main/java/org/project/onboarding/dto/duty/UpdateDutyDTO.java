package org.project.onboarding.dto.duty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.enums.DutyStatusEnum;
import org.project.onboarding.enums.DutyTypeEnum;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDutyDTO {
    private DutyTypeEnum type;
    private LocalDate dutyDateFrom;
    private LocalDate dutyDateTo;
    private DutyStatusEnum status;
    private Float workingHoursPerDay;

}
