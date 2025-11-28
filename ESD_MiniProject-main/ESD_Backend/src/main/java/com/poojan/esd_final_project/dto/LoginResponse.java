package com.poojan.esd_final_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty("token") String token,

        @JsonProperty("email") String email,

        @JsonProperty("role") String role) {
}
