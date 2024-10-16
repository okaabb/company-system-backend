package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.duty.GetDutyDTO;
import org.project.onboarding.dto.duty.ListDutyDTO;
import org.project.onboarding.dto.duty.PostDutyDTO;
import org.project.onboarding.dto.duty.UpdateDutyDTO;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.entity.Duty;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class DutyMapperImpl implements DutyMapper {

    @Override
    public Duty mapPostDutyDTOtoDutyEntityDTO(PostDutyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Duty.DutyBuilder duty = Duty.builder();

        duty.type( dto.getType() );
        duty.dutyDateFrom( dto.getDutyDateFrom() );
        duty.dutyDateTo( dto.getDutyDateTo() );
        duty.workingHoursPerDay( dto.getWorkingHoursPerDay() );

        return duty.build();
    }

    @Override
    public GetDutyDTO mapDutyEntityToGetDutyDTO(Duty duty) {
        if ( duty == null ) {
            return null;
        }

        GetDutyDTO.GetDutyDTOBuilder getDutyDTO = GetDutyDTO.builder();

        getDutyDTO.type( duty.getType() );
        getDutyDTO.employee( employeeToEmployeeDTO( duty.getEmployee() ) );
        getDutyDTO.approvedByEmployee( employeeToEmployeeDTO( duty.getApprovedByEmployee() ) );
        getDutyDTO.recordDate( duty.getRecordDate() );
        getDutyDTO.dutyDateFrom( duty.getDutyDateFrom() );
        getDutyDTO.dutyDateTo( duty.getDutyDateTo() );
        getDutyDTO.status( duty.getStatus() );
        getDutyDTO.workingHoursPerDay( duty.getWorkingHoursPerDay() );

        return getDutyDTO.build();
    }

    @Override
    public List<ListDutyDTO> mapDutyEntitiesToListDutyDTOs(List<Duty> dutyList) {
        if ( dutyList == null ) {
            return null;
        }

        List<ListDutyDTO> list = new ArrayList<ListDutyDTO>( dutyList.size() );
        for ( Duty duty : dutyList ) {
            list.add( mapDutyEntityToDutyDTO( duty ) );
        }

        return list;
    }

    @Override
    public ListDutyDTO mapDutyEntityToDutyDTO(Duty duty) {
        if ( duty == null ) {
            return null;
        }

        ListDutyDTO.ListDutyDTOBuilder listDutyDTO = ListDutyDTO.builder();

        listDutyDTO.employee( dutyEmployeeName( duty ) );
        listDutyDTO.approvedBy( dutyApprovedByEmployeeName( duty ) );
        listDutyDTO.type( duty.getType() );
        listDutyDTO.status( duty.getStatus() );
        listDutyDTO.workingHoursPerDay( duty.getWorkingHoursPerDay() );

        return listDutyDTO.build();
    }

    @Override
    public void copyFields(Duty updatedDuty, Duty duty) {
        if ( updatedDuty == null ) {
            return;
        }

        if ( updatedDuty.getId() != null ) {
            duty.setId( updatedDuty.getId() );
        }
        if ( updatedDuty.getType() != null ) {
            duty.setType( updatedDuty.getType() );
        }
        if ( updatedDuty.getEmployee() != null ) {
            duty.setEmployee( updatedDuty.getEmployee() );
        }
        if ( updatedDuty.getApprovedByEmployee() != null ) {
            duty.setApprovedByEmployee( updatedDuty.getApprovedByEmployee() );
        }
        if ( updatedDuty.getRecordDate() != null ) {
            duty.setRecordDate( updatedDuty.getRecordDate() );
        }
        if ( updatedDuty.getDutyDateFrom() != null ) {
            duty.setDutyDateFrom( updatedDuty.getDutyDateFrom() );
        }
        if ( updatedDuty.getDutyDateTo() != null ) {
            duty.setDutyDateTo( updatedDuty.getDutyDateTo() );
        }
        if ( updatedDuty.getStatus() != null ) {
            duty.setStatus( updatedDuty.getStatus() );
        }
        if ( updatedDuty.getWorkingHoursPerDay() != null ) {
            duty.setWorkingHoursPerDay( updatedDuty.getWorkingHoursPerDay() );
        }
        if ( updatedDuty.getUpdatedAt() != null ) {
            duty.setUpdatedAt( updatedDuty.getUpdatedAt() );
        }
        if ( updatedDuty.getUpdatedBy() != null ) {
            duty.setUpdatedBy( updatedDuty.getUpdatedBy() );
        }
        if ( updatedDuty.getIsDeleted() != null ) {
            duty.setIsDeleted( updatedDuty.getIsDeleted() );
        }
    }

    @Override
    public Duty mapUpdateDutyDTOtoDutyEntityDTO(UpdateDutyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Duty.DutyBuilder duty = Duty.builder();

        duty.type( dto.getType() );
        duty.dutyDateFrom( dto.getDutyDateFrom() );
        duty.dutyDateTo( dto.getDutyDateTo() );
        duty.status( dto.getStatus() );
        duty.workingHoursPerDay( dto.getWorkingHoursPerDay() );

        return duty.build();
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

    private String dutyEmployeeName(Duty duty) {
        if ( duty == null ) {
            return null;
        }
        Employee employee = duty.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String name = employee.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String dutyApprovedByEmployeeName(Duty duty) {
        if ( duty == null ) {
            return null;
        }
        Employee approvedByEmployee = duty.getApprovedByEmployee();
        if ( approvedByEmployee == null ) {
            return null;
        }
        String name = approvedByEmployee.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
