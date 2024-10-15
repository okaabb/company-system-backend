package org.project.onboarding.dto.applicant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicantDTO {
    private String name;

    @Email(regexp = ".+[@].+[\\.].+", message = "Enter a valid email.")
    private String email;

    @Pattern(regexp = "^(\\+2)?0(10|11|12|15)\\d{8}$", message = "Enter a valid mobile number.")
    private String mobileNumber;

}
