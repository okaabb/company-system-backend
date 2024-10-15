package org.project.onboarding.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetApplicantResponseDTO {
    private String name;
    private String email;
    private String mobileNumber;
}
