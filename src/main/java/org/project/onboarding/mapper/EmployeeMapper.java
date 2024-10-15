package org.project.onboarding.mapper;

import org.mapstruct.*;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.dto.department.ListDepartmentDTO;
import org.project.onboarding.dto.employee.GetEmployeeDTO;
import org.project.onboarding.dto.employee.ListEmployeeDTO;
import org.project.onboarding.dto.employee.PostEmployeeDTO;
import org.project.onboarding.dto.employee.UpdateEmployeeDTO;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.project.onboarding.service.DepartmentServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    @Mapping(target = "department", ignore = true)
    Employee mapEmployeeDTOtoEmployeeEntity(PostEmployeeDTO dto);

    default String map(Department value) {
        if (value == null) return null;
        return value.getName();
    }
    @Mapping(target = "department", source = "employee.department.name")
    ListEmployeeDTO mapDepartmentEntityToListDepartmentDTO(Employee employee);

    List<ListEmployeeDTO> mapEmployeeEntitiesToListEmployeeDTOs(List<Employee> employees);

    GetEmployeeDTO mapEmployeeEntityToGetEmployeeDTO(Employee employee);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void copyFields(Employee updatedEmployee, @MappingTarget Employee employee);

    @Mapping(target = "department", ignore = true)
    Employee mapUpdatedEmployeeDTOtoEntityDTO(UpdateEmployeeDTO employeeDTO);
}
