package org.project.onboarding.dto.applicant;

import lombok.Data;

import java.util.List;

@Data
public class ApplicantPaginationDTO {
    private List<ListApplicantsResponseDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
}
