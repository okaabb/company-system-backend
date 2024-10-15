package org.project.onboarding.controller;

import org.project.onboarding.dto.department.DepartmentPaginationDTO;
import org.project.onboarding.dto.department.GetDepartmentDTO;
import org.project.onboarding.dto.department.DepartmentDTO;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Void> saveDepartment(@RequestBody DepartmentDTO department) throws BusinessException {
        departmentService.saveDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<DepartmentPaginationDTO> getAllDepartments(Pageable pageable, @RequestParam Map<String, String> search) {
        return ResponseEntity.ok(departmentService.getAllDepartments(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDepartmentDTO> getDepartmentById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO dto) throws BusinessException {
        departmentService.updateDepartment(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) throws BusinessException {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
