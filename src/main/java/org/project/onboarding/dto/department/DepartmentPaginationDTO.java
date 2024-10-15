package org.project.onboarding.dto.department;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentPaginationDTO {
    private List<ListDepartmentDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
}
