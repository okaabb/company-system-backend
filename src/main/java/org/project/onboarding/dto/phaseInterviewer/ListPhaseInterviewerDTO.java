package org.project.onboarding.dto.phaseInterviewer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPhaseInterviewerDTO {
    private Long id;
    private String hiringPhaseName;
    private String interviewerName;
}
