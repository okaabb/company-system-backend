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
public class PostEmployeeDTO {
    @NotNull(message = "Employee must have a name.")
    private String name;

    @NotNull(message = "Employee must have an email.")
    @Email(regexp = ".+[@].+[\\.].+", message = "Enter a valid email.")
    private String email;

    @NotNull(message = "Employee must have a mobile number.")
    @Pattern(regexp = "^(\\+2)?0(10|11|12|15)\\d{8}$", message = "Enter a valid mobile number.")
    private String mobileNumber;

    @NotNull(message = "Employee must have a national id.")
    @Pattern(regexp = "^([0-9]){14}", message = "Enter a valid national id.")
    private String nationalId;

    @NotNull(message = "Employee must have a username.")
    @Pattern(regexp = "^[^\\s]+$", message = "Username can not have spaces. Try adding special symbols instead.")
    private String username;

    @NotNull(message = "Employee must have a hire date.")
    private LocalDate hireDate;

    @NotNull(message = "Employee must have a position.")
    private PositionEnum position;

    @Size(min = 5, max = 200, message = "Password must be between 5 and 200 characters.")
    @NotNull(message = "Employee must have a password.")
    private String password;

    private String department;

}
