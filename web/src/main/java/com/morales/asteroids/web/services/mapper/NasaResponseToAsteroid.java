package com.morales.asteroids.web.services.mapper;

import com.morales.asteroids.web.dto.Asteroid;
import com.morales.nasa.client.models.NasaAsteroid;

import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface NasaResponseToAsteroid {
    @Mapping(target = "diameter", ignore = true)
    @Mapping(target = "planet", expression = "java(nasaAsteroid.getApproachData().get(0).getOrbitingBody())")
    @Mapping(target = "speed", expression = "java(nasaAsteroid.getApproachData().get(0).getRelativeVelocity().getKmh())")
    @Mapping(target = "date", expression = "java(nasaAsteroid.getApproachData().get(0).getApproachDate())")
    Asteroid toAsteroid(NasaAsteroid nasaAsteroid);

    @AfterMapping
    default void afterMapping(@MappingTarget Asteroid asteroid, NasaAsteroid nasaAsteroid) {
        asteroid.setDiameter((nasaAsteroid.getDiameter().getKilometers().getMin() + nasaAsteroid.getDiameter().getKilometers().getMax())/2);
    }
}
