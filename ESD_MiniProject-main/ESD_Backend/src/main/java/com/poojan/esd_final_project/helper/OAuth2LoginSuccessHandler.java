package com.poojan.esd_final_project.helper;

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

        String role = "student";
        if (email != null && email.toLowerCase().startsWith("placement")) {
            role = "placement";
        }

        try {
            // Try to get existing employee
            employeeService.createOrGetOAuth2Employee(email, givenName, familyName);
        } catch (EmployeeNotFoundException e) {
            // Employee not found - that's fine, we'll just generate the token with the
            // determined role
            // We could optionally create a guest record here if needed
        }

        // Generate JWT token with the determined role
        String token = jwtHelper.generateToken(email, role);
        System.out.println("Generated JWT Token: " + token);

        // Redirect to frontend with token
        String redirectUrl = "http://localhost:3000/login?token=" + token;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
