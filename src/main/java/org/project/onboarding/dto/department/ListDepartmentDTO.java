package org.project.onboarding.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ListDepartmentDTO extends DepartmentDTO {
    private Long id;
}
