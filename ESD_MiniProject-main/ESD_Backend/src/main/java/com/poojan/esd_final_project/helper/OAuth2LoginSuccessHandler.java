package com.poojan.esd_final_project.helper;

import com.poojan.esd_final_project.entity.Employee;
import com.poojan.esd_final_project.exception.EmployeeNotFoundException;
import com.poojan.esd_final_project.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final EmployeeService employeeService;
    private final JWTHelper jwtHelper;

    public OAuth2LoginSuccessHandler(EmployeeService employeeService, JWTHelper jwtHelper) {
        this.employeeService = employeeService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user information from OAuth2 provider
        String email = oAuth2User.getAttribute("email");
        String givenName = oAuth2User.getAttribute("given_name");
        String familyName = oAuth2User.getAttribute("family_name");

        try {
            // Only allow login for existing employees (no auto-registration)
            Employee employee = employeeService.createOrGetOAuth2Employee(email, givenName, familyName);

            // Generate JWT token for employee
            String jwtToken = jwtHelper.generateToken(employee.getEmail(), "employee");

            // Redirect to frontend with token
            String redirectUrl = "http://localhost:3000/placement?token=" + jwtToken;
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);

        } catch (EmployeeNotFoundException e) {
            // Employee not found - treat as Guest
            // Generate JWT token for guest
            String jwtToken = jwtHelper.generateToken(email, "guest");

            // Redirect to frontend with token
            String redirectUrl = "http://localhost:3000/placement?token=" + jwtToken;
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }
    }
}
