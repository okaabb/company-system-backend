package org.project.onboarding.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}

