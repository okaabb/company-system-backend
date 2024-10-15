package org.project.onboarding.dto.phaseInterviewer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.hiringPhase.HiringPhaseLookUpDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPhaseInterviewerDTO {
    private HiringPhaseLookUpDTO hiringPhase;
    private EmployeeDTO interviewer;
    private String notes;
    private Float score;
}
