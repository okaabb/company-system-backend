package org.project.onboarding.dto.employee;

import lombok.Data;

import java.util.List;

@Data
public class EmployeePaginationDTO {
    private List<ListEmployeeDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
}
