package org.project.onboarding.dto.training;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTrainingDTO {
    @NotNull(message = "Training must have a name.")
    private String name;

    @NotNull(message = "Training must have a creator.")
    private Long creator;

    @NotNull(message = "Training must have a created date.")
    private LocalDate createdDate;

    @NotNull(message = "Training must have a number of training hours.")
    private Float trainingHours;
}
