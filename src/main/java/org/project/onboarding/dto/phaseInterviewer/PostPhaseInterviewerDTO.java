package org.project.onboarding.dto.phaseInterviewer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPhaseInterviewerDTO {
    @NotNull(message = "Phase Interviewer must have a hiring phase.")
    private Long hiringPhaseId;

    @NotNull(message = "Must have an interviewer.")
    private Long interviewerId;
    private String notes;
    private Float score;
}
