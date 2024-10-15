package org.project.onboarding.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.dto.department.ListDepartmentDTO;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    default List<Long> map(List<Employee> value) {
        List<Long> list = new ArrayList<>();
        for (Employee emp : value) {
            list.add(emp.getId());
        }
        return list;
    }

    @Mapping(target = "manager", ignore = true)
    Department mapDepartmentPostDTOtoDepartmentEntity(DepartmentDTO dto);

    @Mapping(target = "managerId", source = "manager.id")
    ListDepartmentDTO mapDepartmentEntityToListDepartmentDTO(Department department);

    List<ListDepartmentDTO> mapDepartmentEntitiesToListDepartmentDTOs(List<Department> departments);

    GetDepartmentDTO mapDepartmentEntityToGetDepartmentDTO(Department department);
}
