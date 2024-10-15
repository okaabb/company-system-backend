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
public class PostDutyDTO {
    @NotNull(message = "Duty must belong to an employee.")
    private Long employeeId;

    @NotNull(message = "Duty must have a type.")
    private DutyTypeEnum type;

    @NotNull(message = "Duty must have a start date.")
    private LocalDate dutyDateFrom;

    @NotNull(message = "Duty must have an end date.")
    private LocalDate dutyDateTo;

    @NotNull(message = "Duty must have a number of working hours.")
    private Float workingHoursPerDay;
}
