package com.morales.asteroids.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asteroid {
    @Schema(example = "4341 Poseidon (1987 KF)")
    private String name;
    @Schema(example = "3.6647130844")
    private float diameter;
    @Schema(example = "64181.5947426121")
    private float speed;
    @Schema(example = "2021-12-12")
    private String date;
    @Schema(example = "Earth")
    private String planet;
}
