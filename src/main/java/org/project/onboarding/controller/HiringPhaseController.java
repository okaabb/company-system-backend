package org.project.onboarding.controller;

import jakarta.validation.Valid;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.hiringPhase.GetHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.ListHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.PostHiringPhaseDTO;
import org.project.onboarding.dto.hiringPhase.UpdateHiringPhase;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.HiringPhaseService;
import org.project.onboarding.service.HiringPhaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hiring-phases")
public class HiringPhaseController {
    private final HiringPhaseService hiringPhaseService;

    @Autowired
    public HiringPhaseController(HiringPhaseService hiringPhaseService) {
        this.hiringPhaseService = hiringPhaseService;
    }

    @PostMapping
    public ResponseEntity<Void> saveHiringPhase(@Valid @RequestBody PostHiringPhaseDTO hiringPhase) throws BusinessException {
        hiringPhaseService.saveHiringPhase(hiringPhase);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListHiringPhaseDTO>> getAllHiringPhases(Pageable pageable, Map<String, String> search) {
        return ResponseEntity.ok(hiringPhaseService.getAllHiringPhases(pageable, search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetHiringPhaseDTO> getHiringPhaseById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(hiringPhaseService.getHiringPhaseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHiringPhase(@PathVariable Long id, @Valid @RequestBody UpdateHiringPhase hiringPhase) throws BusinessException {
        hiringPhaseService.updateHiringPhase(id, hiringPhase);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHiringPhase(@PathVariable Long id) throws BusinessException {
        hiringPhaseService.deleteHiringPhase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
