package com.poojan.esd_final_project.controller;

import com.poojan.esd_final_project.dto.EmployeeRequest;
import com.poojan.esd_final_project.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest request) {
        boolean isCreated = employeeService.createEmployee(request);
        String res;
        if (isCreated) {
            res = "Employee created successfully";
        } else {
            res = "Employee creation failed";
        }
        return ResponseEntity.ok(res);
    }
}
