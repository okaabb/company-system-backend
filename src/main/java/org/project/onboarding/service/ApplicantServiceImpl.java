package org.project.onboarding.service;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.applicant.ApplicantDTO;
import org.project.onboarding.dto.applicant.GetApplicantResponseDTO;
import org.project.onboarding.dto.applicant.ListApplicantsResponseDTO;
import org.project.onboarding.dto.applicant.UpdateApplicantDTO;
import org.project.onboarding.entity.Applicant;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.ApplicantService;
import org.project.onboarding.mapper.ApplicantMapper;
import org.project.onboarding.predicates.Applicant.ApplicantPredicateBuilder;
import org.project.onboarding.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.project.onboarding.helperFunctions.PredicatesHelper.*;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }

    @Override
    public void saveApplicant(ApplicantDTO dto) throws BusinessException {
        Applicant applicant = applicantMapper.mapPostRequestToEntity(dto);
        validateEmailUniquenessOnSave(applicant.getEmail());
        validateMobileNumberUniquenessOnSave(applicant.getMobileNumber());
        applicantRepository.save(applicant);
    }

    @Override
    public PaginationDTO<ListApplicantsResponseDTO> getAllApplicants(Map<String, String> search, Pageable pageable) {
        ApplicantPredicateBuilder builder = new ApplicantPredicateBuilder();
        extractQueryParameters(builder, search);

        Page<Applicant> applicants = applicantRepository.findAll(builder.build(), pageable);

        List<Applicant> applicantList = applicants.getContent();
        List<ListApplicantsResponseDTO> content = applicantMapper.mapApplicantEntitiesToListApplicantDTOs(applicantList);
        return createPageResponse(content, pageable, applicants.getTotalElements(), applicants.getTotalPages());
    }

    @Override
    public GetApplicantResponseDTO getApplicantById(Long id) throws BusinessException {
        Applicant applicant = getEntityWithId(id);
        return applicantMapper.mapEntityToGetResponse(applicant);
    }

    @Override
    public void updateApplicant(Long id, UpdateApplicantDTO dto) throws BusinessException {
        Applicant applicant = getEntityWithId(id);
        Applicant updatedApplicant = applicantMapper.mapUpdateRequestToEntity(dto);

        if (!isNull(dto.getEmail())) {
            String email = updatedApplicant.getEmail();
            validateEmailUniquenessOnUpdate(email, id);
        }

        if (!isNull(dto.getMobileNumber())) {
            String mobile = updatedApplicant.getMobileNumber();
            validateMobileNumberUniquenessOnUpdate(mobile, id);
        }
        applicantMapper.copyFields(updatedApplicant, applicant);
        applicantRepository.save(applicant);
    }

    @Override
    public void deleteApplicant(Long id) throws BusinessException {
        Applicant applicant = getEntityWithId(id);
        applicant.setIsDeleted(true);
        applicantRepository.save(applicant);
    }

    @Override
    public Applicant getEntityWithId(Long applicantId) throws BusinessException {
        Applicant applicant = applicantRepository.findByIdAndIsDeletedFalse(applicantId);
        if (isNull(applicant))
            throw new BusinessException("Applicant does not exist.", HttpStatus.NOT_FOUND);
        return applicant;
    }

    private void validateEmailUniquenessOnSave(String email) throws BusinessException {
        Applicant applicantByEmail = applicantRepository.findByEmailAndIsDeletedFalse(email);
        if (!isNull(applicantByEmail))
            throw new BusinessException("An Applicant with this email already exists.", HttpStatus.CONFLICT);

    }

    private void validateMobileNumberUniquenessOnSave(String mobileNumber) throws BusinessException {
        Applicant applicantByMobile = applicantRepository.findByMobileNumberAndIsDeletedFalse(mobileNumber);
        if (!isNull(applicantByMobile))
            throw new BusinessException("An Applicant with this mobile number already exists.", HttpStatus.CONFLICT);
    }

    private void validateEmailUniquenessOnUpdate(String email, Long id) throws BusinessException {
        Applicant applicantByEmail = applicantRepository.findByEmailAndIsDeletedFalseAndIdIsNot(email, id);
        if (!isNull(applicantByEmail))
            throw new BusinessException("An Applicant with this email already exists.", HttpStatus.CONFLICT);

    }

    private void validateMobileNumberUniquenessOnUpdate(String mobileNumber, Long id) throws BusinessException {
        Applicant applicantByMobile = applicantRepository.findByMobileNumberAndIsDeletedFalseAndIdIsNot(mobileNumber, id);
        if (!isNull(applicantByMobile))
            throw new BusinessException("An Applicant with this mobile number already exists.", HttpStatus.CONFLICT);
    }

    public static void extractQueryParameters(ApplicantPredicateBuilder builder, Map<String, String> search) {
        if (search != null) {
            for (Map.Entry<String, String> entry : search.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (isPageableKey(key)) continue;
                builder.with(key, "=", value);
            }
        }
        builder.with("isDeleted", "=", "false");
    }


}