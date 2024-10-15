package org.project.onboarding.controller;

import jakarta.validation.Valid;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.application.*;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.ApplicationService;
import org.project.onboarding.service.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationServiceImpl applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Void> saveApplication(@Valid @RequestBody PostApplicationDTO application) throws BusinessException {
        applicationService.saveApplication(application);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListApplicationDTO>> getAllApplications(Pageable pageable, @RequestParam Map<String, String> search) {
        return ResponseEntity.ok(applicationService.getAllApplications(pageable, search));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<GetApplicationDTO> getApplicationById(@PathVariable Long applicationId) throws BusinessException {
        return ResponseEntity.ok(applicationService.getApplicationById(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<Void> updateApplication(@PathVariable Long applicationId, @Valid @RequestBody UpdateApplicationDTO application) throws BusinessException {
        applicationService.updateApplication(applicationId, application);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationId) throws BusinessException {
        applicationService.deleteApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
