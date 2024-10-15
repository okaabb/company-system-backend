package org.project.onboarding.dto.employee;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.project.onboarding.enums.PositionEnum;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private String nationalId;
    private String username;
    private String department;
    private LocalDate hireDate;
    private PositionEnum position;
}
