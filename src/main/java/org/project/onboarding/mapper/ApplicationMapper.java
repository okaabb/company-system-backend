package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.application.*;
import org.project.onboarding.entity.Application;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ApplicationMapper {
    @Mapping(target = "applicant", ignore = true)
    @Mapping(target = "hr", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "referralEmployee", ignore = true)
    Application mapPostApplicationToApplicationEntity(PostApplicationDTO dto);

    @Mapping(target = "department", source = "application.department.name")
    GetApplicationDTO mapApplicationEntityToApplicationGet(Application application);

    List<ListApplicationDTO> mapApplicationEntitiesToListApplicationDTOs(List<Application> applicationList);

    @Mapping(target = "applicantName", source = "application.applicant.name")
    @Mapping(target = "departmentName", source = "application.department.name")
    ListApplicationDTO mapApplicationEntityToApplicationDTO(Application application);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(Application updatedApplication, @MappingTarget Application application);

    Application mapUpdateApplicationDTOtoApplicationEntityDTO(UpdateApplicationDTO dto);
}
