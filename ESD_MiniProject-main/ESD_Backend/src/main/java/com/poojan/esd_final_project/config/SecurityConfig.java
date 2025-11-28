package com.poojan.esd_final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poojan.esd_final_project.helper.JwtAuthenticationFilter;
import com.poojan.esd_final_project.helper.OAuth2LoginFailureHandler;
import com.poojan.esd_final_project.helper.OAuth2LoginSuccessHandler;
import com.poojan.esd_final_project.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
        private final CustomOAuth2UserService customOAuth2UserService;
        private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

        public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                        OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler,
                        CustomOAuth2UserService customOAuth2UserService,
                        OAuth2LoginFailureHandler oAuth2LoginFailureHandler) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
                this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
                this.customOAuth2UserService = customOAuth2UserService;
                this.oAuth2LoginFailureHandler = oAuth2LoginFailureHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .cors(cors -> {
                                }) // Use CORS configuration from addCorsMappings
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/v1/auth/**", "/api/auth/**", "/employee/",
                                                                "/employee",
                                                                "/oauth2/**", "/login/oauth2/**", "/v3/api-docs/**",
                                                                "/swagger-ui/**", "/swagger-ui.html")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo
                                                        .userService(customOAuth2UserService))
                                                .successHandler(oAuth2LoginSuccessHandler)
                                                .failureHandler(oAuth2LoginFailureHandler))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                                .allowedOrigins("http://localhost:3000", "http://localhost:9191") // React frontend and
                                                                                                  // Swagger UI origin
                                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                                .allowedHeaders("Authorization", "Content-Type")
                                .allowCredentials(true);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
