package com.morales.nasa.client.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NasaAsteroid {
    private String name;
    @JsonAlias("is_potentially_hazardous_asteroid")
    private boolean potentiallyHazardousAsteroid;
    @JsonAlias("estimated_diameter")
    private EstimatedMeasures diameter;
    @JsonAlias("close_approach_data")
    private List<ApproachData> approachData;
}
