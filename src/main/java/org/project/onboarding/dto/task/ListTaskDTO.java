package org.project.onboarding.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListTaskDTO {
    private Long id;
    private String name;
    private String sprintName;
}
