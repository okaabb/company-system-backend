package org.project.onboarding.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.onboarding.enums.PositionEnum;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDTO {

    @Email(regexp = ".+[@].+[\\.].+", message = "Enter a valid email.")
    private String email;

    @Pattern(regexp = "^(\\+2)?0(10|11|12|15)\\d{8}$", message = "Enter a valid mobile number.")
    private String mobileNumber;

    @Pattern(regexp = "^([0-9]){14}", message = "Enter a valid national id.")
    private String nationalId;

    @Pattern(regexp = "^[^\\s]+$", message = "Username can not have spaces. Try adding special symbols instead.")
    private String username;

    @Size(min = 5, max = 200, message = "Password must be between 5 and 200 characters.")
    private String password;

    private String department;
    private LocalDate hireDate;
    private PositionEnum position;
    private String name;
}
