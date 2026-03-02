package com.poojan.esd_final_project.service;

import com.poojan.esd_final_project.entity.Employee;
import com.poojan.esd_final_project.repo.EmployeeRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee authenticate(String email, String password) {
        java.util.List<Employee> employees = employeeRepo.findByEmail(email);

        if (employees.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        // Handle duplicates by taking the first one
        Employee employee = employees.get(0);

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return employee;
    }

    public String determineRole(String email) {
        if (email.toLowerCase().startsWith("placement")) {
            return "placement";
        }
        return "student";
    }
}
