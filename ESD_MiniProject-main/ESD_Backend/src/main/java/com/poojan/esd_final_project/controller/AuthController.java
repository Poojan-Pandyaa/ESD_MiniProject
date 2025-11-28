package com.poojan.esd_final_project.controller;

import com.poojan.esd_final_project.dto.LoginRequest;
import com.poojan.esd_final_project.dto.LoginResponse;
import com.poojan.esd_final_project.entity.Employee;
import com.poojan.esd_final_project.helper.JWTHelper;
import com.poojan.esd_final_project.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JWTHelper jwtHelper;

    public AuthController(AuthService authService, JWTHelper jwtHelper) {
        this.authService = authService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            Employee employee = authService.authenticate(
                    loginRequest.email(),
                    loginRequest.password());

            // Determine role based on email prefix
            String role = authService.determineRole(employee.getEmail());

            // Generate JWT token
            String token = jwtHelper.generateToken(employee.getEmail(), role);

            // Log token to console for debugging
            System.out.println("=== Traditional Login ===");
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Role: " + role);
            System.out.println("Generated Token: " + token);

            // Return response
            LoginResponse response = new LoginResponse(token, employee.getEmail(), role);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    // Inner class for error responses
    private record ErrorResponse(String message) {
    }
}
