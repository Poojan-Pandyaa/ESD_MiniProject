package com.poojan.esd_final_project.service;

import com.poojan.esd_final_project.dto.LoginRequest;
import com.poojan.esd_final_project.entity.Employee;
import com.poojan.esd_final_project.helper.Encryption;
import com.poojan.esd_final_project.helper.JWTHelper;
import com.poojan.esd_final_project.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private JWTHelper jwtHelper;

    // Use real Encryption with real BCryptPasswordEncoder
    @Spy
    private Encryption encryption = new Encryption(new BCryptPasswordEncoder());

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testLoginSuccess() {
        String email = "test@example.com";
        String rawPassword = "password123";
        
        // Generate hash
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        
        System.out.println("GENERATED HASH FOR password123: " + encodedPassword);

        Employee mockEmployee = new Employee();
        mockEmployee.setEmail(email);
        mockEmployee.setPassword(encodedPassword);

        when(employeeRepo.findByEmail(email)).thenReturn(Optional.of(mockEmployee));
        when(jwtHelper.generateToken(email)).thenReturn("dummy-token");

        LoginRequest request = new LoginRequest(email, rawPassword);
        String token = employeeService.login(request);

        assertNotNull(token);
        assertEquals("dummy-token", token);
    }
}
