package com.poojan.esd_final_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StudentPlacementDTO(
        @JsonProperty("first_name") String firstName,

        @JsonProperty("last_name") String lastName,

        @JsonProperty("program") String program,

        @JsonProperty("placement_org") String placementOrg,

        @JsonProperty("alumni_org") String alumniOrg,

        @JsonProperty("graduation_year") Integer graduationYear,

        @JsonProperty("is_alumni") String isAlumni,

        @JsonProperty("placement_status") String placementStatus,

        @JsonProperty("ctc") Double ctc) {
}
