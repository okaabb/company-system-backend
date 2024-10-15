package org.project.onboarding.controller;

import jakarta.validation.Valid;
import org.project.onboarding.dto.PaginationDTO;
import org.project.onboarding.dto.employee.*;
import org.project.onboarding.exception.BusinessException;
import org.project.onboarding.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }


    @PostMapping
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody PostEmployeeDTO employee) throws BusinessException {
        employeeServiceImpl.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationDTO<ListEmployeeDTO>> getAllEmployees(Pageable pageable, @RequestParam Map<String, String> search) throws BusinessException {
        return ResponseEntity.ok(employeeServiceImpl.getAllEmployees(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEmployeeDTO> getEmployeeById(@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(employeeServiceImpl.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDTO employee) throws BusinessException {
        employeeServiceImpl.updateEmployee(id, employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws BusinessException {
        employeeServiceImpl.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
