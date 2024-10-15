package org.project.onboarding.helperFunctions;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.applicant.ApplicantPaginationDTO;
import org.project.onboarding.dto.applicant.ListApplicantsResponseDTO;
import org.project.onboarding.predicates.Applicant.ApplicantPredicateBuilder;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.*;

public class PredicatesHelper {

    public static <T> PaginationDTO<T> createPageResponse(List<T> content, Pageable pageable, long totalElements, int totalPages) {
        PaginationDTO<T> response = new PaginationDTO<>();
        response.setContent(content);
        response.setPageNumber(pageable.getPageNumber());
        response.setPageSize(pageable.getPageSize());
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        return response;

    }

    public static List<Field> getAllFieldsIncludingInherited(Class<?> childClass) {
        List<Field> fields = new ArrayList<>();
        while (childClass != null) {
            fields.addAll(Arrays.asList(childClass.getDeclaredFields()));
            childClass = childClass.getSuperclass();
        }
        return fields;
    }


    public static boolean isPageableKey(String key) {
        Set<String> pageableKeys = Set.of("page", "size", "sort");
        return pageableKeys.contains(key.toLowerCase());
    }
}
