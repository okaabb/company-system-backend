package org.project.onboarding.controller;

import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.sprint.GetSprintDTO;
import org.project.onboarding.dto.sprint.ListSprintDTO;
import org.project.onboarding.dto.sprint.PostSprintDTO;
import org.project.onboarding.dto.sprint.UpdateSprintDTO;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("sprints")
public class SprintController {
    private final SprintService sprintService;

    @Autowired
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping
    public ResponseEntity<Void> saveSprint(@RequestBody PostSprintDTO sprint) throws BusinessException {
        sprintService.saveSprint(sprint);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListSprintDTO>> getAllSprints(Map<String, String> search, Pageable pageable) {
        return ResponseEntity.ok(sprintService.getAllSprints(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSprintDTO> getSprintById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(sprintService.getSprintById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSprint(@PathVariable Long id, @RequestBody UpdateSprintDTO sprint) throws BusinessException {
        sprintService.updateSprint(id, sprint);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) throws BusinessException {
        sprintService.deleteSprint(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}/assign-sprint/{teamId}")
    public ResponseEntity<GetSprintDTO> assignSprintToTeam(@PathVariable Long id, @PathVariable Long teamId) throws BusinessException {
        return ResponseEntity.ok(sprintService.assignSprintToTeam(id, teamId));
    }
}
