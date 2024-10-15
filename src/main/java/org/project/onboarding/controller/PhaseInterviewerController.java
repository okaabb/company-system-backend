package org.project.onboarding.controller;

import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.phaseInterviewer.*;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.PhaseInterviewerService;
import org.project.onboarding.service.PhaseInterviewerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/phase-interviewers")
public class PhaseInterviewerController {
    private final PhaseInterviewerService phaseInterviewerService;

    @Autowired
    public PhaseInterviewerController(PhaseInterviewerService phaseInterviewerService) {
        this.phaseInterviewerService = phaseInterviewerService;
    }

    @PostMapping
    public ResponseEntity<Void> savePhaseInterviewer(@RequestBody PostPhaseInterviewerDTO phaseInterviewer) throws BusinessException {
        phaseInterviewerService.savePhaseInterviewer(phaseInterviewer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListPhaseInterviewerDTO>> getAllPhaseInterviewers(Pageable pageable, Map<String, String> search) {
        return ResponseEntity.ok(phaseInterviewerService.getAllPhaseInterviewers(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPhaseInterviewerDTO> getPhaseInterviewerById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(phaseInterviewerService.getPhaseInterviewerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePhaseInterviewer(@PathVariable Long id, @RequestBody UpdatePhaseInterviewerDTO phaseInterviewer) throws BusinessException {
        phaseInterviewerService.updatePhaseInterviewer(id, phaseInterviewer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhaseInterviewer(@PathVariable Long id) throws BusinessException {
        phaseInterviewerService.deletePhaseInterviewer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
