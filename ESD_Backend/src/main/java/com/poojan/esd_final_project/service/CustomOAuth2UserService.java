package com.poojan.esd_final_project.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Load user from OAuth2 provider (Google)
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        // You can add custom processing here if needed
        // For now, we just return the OAuth2User as-is
        // The actual user creation/lookup happens in OAuth2LoginSuccessHandler
        
        return oAuth2User;
    }
}
