package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.duty.GetDutyDTO;
import org.project.onboarding.dto.duty.ListDutyDTO;
import org.project.onboarding.dto.duty.PostDutyDTO;
import org.project.onboarding.dto.duty.UpdateDutyDTO;
import org.project.onboarding.entity.Duty;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DutyMapper {

    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "approvedByEmployee", ignore = true)
    Duty mapPostDutyDTOtoDutyEntityDTO(PostDutyDTO dto);

    GetDutyDTO mapDutyEntityToGetDutyDTO(Duty duty);

    List<ListDutyDTO> mapDutyEntitiesToListDutyDTOs(List<Duty> dutyList);

    @Mapping(target = "employee", source = "duty.employee.name")
    @Mapping(target = "approvedBy", source = "duty.approvedByEmployee.name")
    ListDutyDTO mapDutyEntityToDutyDTO(Duty duty);

    default String map(Employee value) {
        return value.getName();
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(Duty updatedDuty, @MappingTarget Duty duty);

    Duty mapUpdateDutyDTOtoDutyEntityDTO(UpdateDutyDTO dto);
}
