package org.project.onboarding.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.dto.application.GetApplicationDTO;
import org.project.onboarding.dto.application.ListApplicationDTO;
import org.project.onboarding.dto.application.PostApplicationDTO;
import org.project.onboarding.dto.application.UpdateApplicationDTO;
import org.project.onboarding.dto.employee.EmployeeDTO;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.entity.Application;
import org.project.onboarding.entity.Department;
import org.project.onboarding.entity.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T19:18:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public Application mapPostApplicationToApplicationEntity(PostApplicationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Application.ApplicationBuilder application = Application.builder();

        application.applicationStatus( dto.getApplicationStatus() );
        application.linkedinProfileUrl( dto.getLinkedinProfileUrl() );
        application.referralType( dto.getReferralType() );
        application.position( dto.getPosition() );

        return application.build();
    }

    @Override
    public GetApplicationDTO mapApplicationEntityToApplicationGet(Application application) {
        if ( application == null ) {
            return null;
        }

        GetApplicationDTO.GetApplicationDTOBuilder getApplicationDTO = GetApplicationDTO.builder();

        getApplicationDTO.department( applicationDepartmentName( application ) );
        getApplicationDTO.applicant( applicantToApplicantDTO( application.getApplicant() ) );
        getApplicationDTO.hr( employeeToEmployeeDTO( application.getHr() ) );
        getApplicationDTO.referralEmployee( employeeToEmployeeDTO( application.getReferralEmployee() ) );
        getApplicationDTO.applicationStatus( application.getApplicationStatus() );
        getApplicationDTO.linkedinProfileUrl( application.getLinkedinProfileUrl() );
        getApplicationDTO.referralType( application.getReferralType() );
        getApplicationDTO.position( application.getPosition() );

        return getApplicationDTO.build();
    }

    @Override
    public List<ListApplicationDTO> mapApplicationEntitiesToListApplicationDTOs(List<Application> applicationList) {
        if ( applicationList == null ) {
            return null;
        }

        List<ListApplicationDTO> list = new ArrayList<ListApplicationDTO>( applicationList.size() );
        for ( Application application : applicationList ) {
            list.add( mapApplicationEntityToApplicationDTO( application ) );
        }

        return list;
    }

    @Override
    public ListApplicationDTO mapApplicationEntityToApplicationDTO(Application application) {
        if ( application == null ) {
            return null;
        }

        ListApplicationDTO.ListApplicationDTOBuilder listApplicationDTO = ListApplicationDTO.builder();

        listApplicationDTO.applicantName( applicationApplicantName( application ) );
        listApplicationDTO.departmentName( applicationDepartmentName( application ) );
        listApplicationDTO.applicationStatus( application.getApplicationStatus() );
        listApplicationDTO.position( application.getPosition() );

        return listApplicationDTO.build();
    }

    @Override
    public void copyFields(Application updatedApplication, Application application) {
        if ( updatedApplication == null ) {
            return;
        }

        if ( updatedApplication.getId() != null ) {
            application.setId( updatedApplication.getId() );
        }
        if ( updatedApplication.getApplicant() != null ) {
            application.setApplicant( updatedApplication.getApplicant() );
        }
        if ( updatedApplication.getHr() != null ) {
            application.setHr( updatedApplication.getHr() );
        }
        if ( updatedApplication.getDepartment() != null ) {
            application.setDepartment( updatedApplication.getDepartment() );
        }
        if ( updatedApplication.getReferralEmployee() != null ) {
            application.setReferralEmployee( updatedApplication.getReferralEmployee() );
        }
        if ( updatedApplication.getApplicationStatus() != null ) {
            application.setApplicationStatus( updatedApplication.getApplicationStatus() );
        }
        if ( updatedApplication.getLinkedinProfileUrl() != null ) {
            application.setLinkedinProfileUrl( updatedApplication.getLinkedinProfileUrl() );
        }
        if ( updatedApplication.getReferralType() != null ) {
            application.setReferralType( updatedApplication.getReferralType() );
        }
        if ( updatedApplication.getPosition() != null ) {
            application.setPosition( updatedApplication.getPosition() );
        }
        if ( updatedApplication.getUpdatedAt() != null ) {
            application.setUpdatedAt( updatedApplication.getUpdatedAt() );
        }
        if ( updatedApplication.getUpdatedBy() != null ) {
            application.setUpdatedBy( updatedApplication.getUpdatedBy() );
        }
        if ( updatedApplication.getIsDeleted() != null ) {
            application.setIsDeleted( updatedApplication.getIsDeleted() );
        }
    }

    @Override
    public Application mapUpdateApplicationDTOtoApplicationEntityDTO(UpdateApplicationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Application.ApplicationBuilder application = Application.builder();

        application.applicationStatus( dto.getApplicationStatus() );
        application.linkedinProfileUrl( dto.getLinkedinProfileUrl() );

        return application.build();
    }

    private String applicationDepartmentName(Application application) {
        if ( application == null ) {
            return null;
        }
        Department department = application.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected ApplicantDTO applicantToApplicantDTO(Applicant applicant) {
        if ( applicant == null ) {
            return null;
        }

        ApplicantDTO.ApplicantDTOBuilder<?, ?> applicantDTO = ApplicantDTO.builder();

        applicantDTO.name( applicant.getName() );
        applicantDTO.email( applicant.getEmail() );
        applicantDTO.mobileNumber( applicant.getMobileNumber() );

        return applicantDTO.build();
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

    private String applicationApplicantName(Application application) {
        if ( application == null ) {
            return null;
        }
        Applicant applicant = application.getApplicant();
        if ( applicant == null ) {
            return null;
        }
        String name = applicant.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
