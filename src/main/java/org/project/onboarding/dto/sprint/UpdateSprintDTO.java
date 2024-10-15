package org.project.onboarding.dto.sprint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSprintDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate createdDate;
    private LocalDate endDate;
    private Long creator;
    private Long teamId;
}
