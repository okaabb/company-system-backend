package org.project.onboarding.controller;

import jakarta.validation.Valid;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.duty.GetDutyDTO;
import org.project.onboarding.dto.duty.ListDutyDTO;
import org.project.onboarding.dto.duty.PostDutyDTO;
import org.project.onboarding.dto.duty.UpdateDutyDTO;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/duties")
public class DutyController {
    private final DutyService dutyService;

    @Autowired
    public DutyController(DutyService dutyService) {
        this.dutyService = dutyService;
    }

    @PostMapping
    public ResponseEntity<Void> saveDuty(@Valid @RequestBody PostDutyDTO duty) throws BusinessException {
        dutyService.saveDuty(duty);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListDutyDTO>> getAllDuties(Pageable pageable, @RequestParam Map<String, String> search) {
        return ResponseEntity.ok(dutyService.getAllDuties(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDutyDTO> getDutyById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(dutyService.getDutyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDuty(@PathVariable Long id, @Valid @RequestBody UpdateDutyDTO duty) throws BusinessException {
        dutyService.updateDuty(id, duty);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuty(@PathVariable Long id) throws BusinessException {
        dutyService.deleteDuty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/approve/{approvedById}")
    public ResponseEntity<Void> approveDuty(@PathVariable Long id, @PathVariable Long approvedById) throws BusinessException {
        dutyService.approveDuty(id, approvedById);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/reject/{rejectedById}")
    public ResponseEntity<Void> rejectDuty(@PathVariable Long id, @PathVariable Long rejectedById) throws BusinessException {
        dutyService.rejectDuty(id, rejectedById);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

