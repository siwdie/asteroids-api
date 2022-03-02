package com.morales.asteroids.web.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {
    @Schema(example = "400 BAD REQUEST")
    private HttpStatus status;
    @Schema(example = "Error with parameter type")
    private String message;
    @Schema(example = "[\"The parameter \'example\' must be type String\"]")
    private List<String> errors;
}
