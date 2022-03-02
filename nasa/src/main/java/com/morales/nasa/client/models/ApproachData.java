package com.morales.nasa.client.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproachData {
    @JsonAlias("close_approach_date")
    private String approachDate;
    @JsonAlias("orbiting_body")
    private String orbitingBody;
    @JsonAlias("relative_velocity")
    private RelativeVelocity relativeVelocity;
}
