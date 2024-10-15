package org.project.onboarding.dto.sprint;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSprintDTO {
    @NotNull(message = "Sprint must have a name.")
    private String name;

    @NotNull(message = "Sprint must have a start date.")
    private LocalDate startDate;

    @NotNull(message = "Sprint must have a creation date.")
    private LocalDate createdDate;

    @NotNull(message = "Sprint must have an end date.")
    private LocalDate endDate;

    @NotNull(message = "Sprint must have a creator.")
    private Long creator;

    @NotNull(message = "Sprint must have a team.")
    private Long teamId;
}
