package com.morales.nasa.client.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelativeVelocity {
    @JsonAlias("kilometers_per_hour")
    private float kmh;
}
