package org.project.onboarding.dto.application;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationPaginationDTO {
    private List<ListApplicationDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
}
