package org.project.onboarding.dto.phaseInterviewer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PhaseInterviewerLookUpDTO {
    private Long id;
    private String hiringPhaseName;
    private String interviewerName;
}
