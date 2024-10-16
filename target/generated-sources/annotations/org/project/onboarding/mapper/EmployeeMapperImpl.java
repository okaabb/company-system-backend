package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.employee.GetEmployeeDTO;
import org.project.onboarding.dto.employee.ListEmployeeDTO;
import org.project.onboarding.dto.employee.PostEmployeeDTO;
import org.project.onboarding.dto.employee.UpdateEmployeeDTO;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:27+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee mapEmployeeDTOtoEmployeeEntity(PostEmployeeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.name( dto.getName() );
        employee.email( dto.getEmail() );
        employee.mobileNumber( dto.getMobileNumber() );
        employee.nationalId( dto.getNationalId() );
        employee.password( dto.getPassword() );
        employee.username( dto.getUsername() );
        employee.hireDate( dto.getHireDate() );
        employee.position( dto.getPosition() );

        return employee.build();
    }

    @Override
    public ListEmployeeDTO mapDepartmentEntityToListDepartmentDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        ListEmployeeDTO.ListEmployeeDTOBuilder<?, ?> listEmployeeDTO = ListEmployeeDTO.builder();

        listEmployeeDTO.department( employeeDepartmentName( employee ) );
        listEmployeeDTO.id( employee.getId() );
        listEmployeeDTO.name( employee.getName() );
        listEmployeeDTO.email( employee.getEmail() );
        listEmployeeDTO.position( employee.getPosition() );

        return listEmployeeDTO.build();
    }

    @Override
    public List<ListEmployeeDTO> mapEmployeeEntitiesToListEmployeeDTOs(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<ListEmployeeDTO> list = new ArrayList<ListEmployeeDTO>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( mapDepartmentEntityToListDepartmentDTO( employee ) );
        }

        return list;
    }

    @Override
    public GetEmployeeDTO mapEmployeeEntityToGetEmployeeDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        GetEmployeeDTO.GetEmployeeDTOBuilder<?, ?> getEmployeeDTO = GetEmployeeDTO.builder();

        getEmployeeDTO.name( employee.getName() );
        getEmployeeDTO.email( employee.getEmail() );
        getEmployeeDTO.mobileNumber( employee.getMobileNumber() );
        getEmployeeDTO.nationalId( employee.getNationalId() );
        getEmployeeDTO.username( employee.getUsername() );
        getEmployeeDTO.department( map( employee.getDepartment() ) );
        getEmployeeDTO.hireDate( employee.getHireDate() );
        getEmployeeDTO.position( employee.getPosition() );

        return getEmployeeDTO.build();
    }

    @Override
    public void copyFields(Employee updatedEmployee, Employee employee) {
        if ( updatedEmployee == null ) {
            return;
        }

        if ( updatedEmployee.getId() != null ) {
            employee.setId( updatedEmployee.getId() );
        }
        if ( updatedEmployee.getName() != null ) {
            employee.setName( updatedEmployee.getName() );
        }
        if ( updatedEmployee.getEmail() != null ) {
            employee.setEmail( updatedEmployee.getEmail() );
        }
        if ( updatedEmployee.getMobileNumber() != null ) {
            employee.setMobileNumber( updatedEmployee.getMobileNumber() );
        }
        if ( updatedEmployee.getNationalId() != null ) {
            employee.setNationalId( updatedEmployee.getNationalId() );
        }
        if ( updatedEmployee.getPassword() != null ) {
            employee.setPassword( updatedEmployee.getPassword() );
        }
        if ( updatedEmployee.getUsername() != null ) {
            employee.setUsername( updatedEmployee.getUsername() );
        }
        if ( updatedEmployee.getDepartment() != null ) {
            employee.setDepartment( updatedEmployee.getDepartment() );
        }
        if ( updatedEmployee.getHireDate() != null ) {
            employee.setHireDate( updatedEmployee.getHireDate() );
        }
        if ( updatedEmployee.getPosition() != null ) {
            employee.setPosition( updatedEmployee.getPosition() );
        }
        if ( updatedEmployee.getUpdatedAt() != null ) {
            employee.setUpdatedAt( updatedEmployee.getUpdatedAt() );
        }
        if ( updatedEmployee.getUpdatedBy() != null ) {
            employee.setUpdatedBy( updatedEmployee.getUpdatedBy() );
        }
        if ( updatedEmployee.getIsDeleted() != null ) {
            employee.setIsDeleted( updatedEmployee.getIsDeleted() );
        }
    }

    @Override
    public Employee mapUpdatedEmployeeDTOtoEntityDTO(UpdateEmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.name( employeeDTO.getName() );
        employee.email( employeeDTO.getEmail() );
        employee.mobileNumber( employeeDTO.getMobileNumber() );
        employee.nationalId( employeeDTO.getNationalId() );
        employee.password( employeeDTO.getPassword() );
        employee.username( employeeDTO.getUsername() );
        employee.hireDate( employeeDTO.getHireDate() );
        employee.position( employeeDTO.getPosition() );

        return employee.build();
    }

    private String employeeDepartmentName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
