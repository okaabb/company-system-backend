package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.dto.department.ListDepartmentDTO;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.dto.employee.EmployeePositionDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department mapDepartmentPostDTOtoDepartmentEntity(DepartmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Department.DepartmentBuilder department = Department.builder();

        department.name( dto.getName() );

        return department.build();
    }

    @Override
    public ListDepartmentDTO mapDepartmentEntityToListDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        ListDepartmentDTO.ListDepartmentDTOBuilder<?, ?> listDepartmentDTO = ListDepartmentDTO.builder();

        listDepartmentDTO.managerId( departmentManagerId( department ) );
        listDepartmentDTO.name( department.getName() );
        listDepartmentDTO.id( department.getId() );

        return listDepartmentDTO.build();
    }

    @Override
    public List<ListDepartmentDTO> mapDepartmentEntitiesToListDepartmentDTOs(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<ListDepartmentDTO> list = new ArrayList<ListDepartmentDTO>( departments.size() );
        for ( Department department : departments ) {
            list.add( mapDepartmentEntityToListDepartmentDTO( department ) );
        }

        return list;
    }

    @Override
    public GetDepartmentDTO mapDepartmentEntityToGetDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        GetDepartmentDTO.GetDepartmentDTOBuilder getDepartmentDTO = GetDepartmentDTO.builder();

        getDepartmentDTO.name( department.getName() );
        getDepartmentDTO.manager( employeeToEmployeeDTO( department.getManager() ) );
        getDepartmentDTO.employees( employeeListToEmployeePositionDTOList( department.getEmployees() ) );

        return getDepartmentDTO.build();
    }

    private Long departmentManagerId(Department department) {
        if ( department == null ) {
            return null;
        }
        Employee manager = department.getManager();
        if ( manager == null ) {
            return null;
        }
        Long id = manager.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder<?, ?> employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( employee.getId() );
        employeeDTO.name( employee.getName() );
        employeeDTO.email( employee.getEmail() );

        return employeeDTO.build();
    }

    protected EmployeePositionDTO employeeToEmployeePositionDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeePositionDTO.EmployeePositionDTOBuilder<?, ?> employeePositionDTO = EmployeePositionDTO.builder();

        employeePositionDTO.id( employee.getId() );
        employeePositionDTO.name( employee.getName() );
        employeePositionDTO.email( employee.getEmail() );
        employeePositionDTO.position( employee.getPosition() );

        return employeePositionDTO.build();
    }

    protected List<EmployeePositionDTO> employeeListToEmployeePositionDTOList(List<Employee> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeePositionDTO> list1 = new ArrayList<EmployeePositionDTO>( list.size() );
        for ( Employee employee : list ) {
            list1.add( employeeToEmployeePositionDTO( employee ) );
        }

        return list1;
    }
}
