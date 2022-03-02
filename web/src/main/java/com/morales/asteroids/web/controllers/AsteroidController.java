package com.morales.asteroids.web.controllers;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.morales.asteroids.web.dto.Asteroid;
import com.morales.asteroids.web.services.NasaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/asteroids")
@RestController
@Validated
public class AsteroidController {

    @Autowired
    private NasaService nasaService;

    public AsteroidController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    @Operation(summary = "Get asteroids info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asteroid data found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Asteroid.class)))),
            @ApiResponse(responseCode = "204", description = "No data found"),
            @ApiResponse(responseCode = "400", description = "Invalid params provided"),
            @ApiResponse(responseCode = "500", description = "Internal server Error")
    })
    @GetMapping
    public ResponseEntity<List<Asteroid>> asteroids(@Parameter(description = "days") @RequestParam @Min(value = 1, message = "The number must be between 1 and 7") @Max(value = 7, message = "The number must be between 1 and 7") int days) {
        return ResponseEntity.ok(nasaService.getAsteroids(days));
    }

}
