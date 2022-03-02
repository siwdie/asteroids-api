package com.morales.nasa.client.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatedRange {
    @JsonAlias("estimated_diameter_min")
    float min;
    @JsonAlias("estimated_diameter_max")
    float max;
}