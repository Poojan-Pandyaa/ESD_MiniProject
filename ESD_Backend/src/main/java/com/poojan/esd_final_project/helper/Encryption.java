package com.poojan.esd_final_project.helper;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Encryption {
    private final PasswordEncoder passwordEncoder;

    public Encryption(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validates(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
