package org.project.onboarding.dto.training;

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
public class UpdateTrainingDTO {
    private String name;
    private Long creator;
    private LocalDate createdDate;
    private Float trainingHours;
}
