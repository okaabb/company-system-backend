package org.project.onboarding.controller;

import jakarta.validation.Valid;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.applicant.*;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.ApplicantService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/applicants")
//@CrossOrigin(origins = "http://localhost:4200")
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ResponseEntity<Void> saveApplicant(@Valid @RequestBody ApplicantDTO applicant) throws BusinessException {
        applicantService.saveApplicant(applicant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListApplicantsResponseDTO>> getAllApplicants(Pageable pageable, @RequestParam Map<String, String> search) {
        return ResponseEntity.ok(applicantService.getAllApplicants(search, pageable));
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<GetApplicantResponseDTO> getApplicantById(@PathVariable Long applicantId) throws BusinessException {
        return ResponseEntity.ok(applicantService.getApplicantById(applicantId));
    }

    @PutMapping("/{applicantId}")
    public ResponseEntity<Void> updateApplicant(@PathVariable Long applicantId, @Valid @RequestBody UpdateApplicantDTO applicant) throws BusinessException {
        applicantService.updateApplicant(applicantId, applicant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long applicantId) throws BusinessException {
        applicantService.deleteApplicant(applicantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
