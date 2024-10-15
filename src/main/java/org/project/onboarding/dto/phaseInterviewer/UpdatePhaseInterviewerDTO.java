package org.project.onboarding.dto.phaseInterviewer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhaseInterviewerDTO {
    private Long hiringPhaseId;
    private Long interviewerId;
    private String notes;
    private Float score;
}
