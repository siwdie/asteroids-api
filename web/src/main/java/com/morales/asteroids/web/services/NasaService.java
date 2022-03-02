package com.morales.asteroids.web.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.morales.asteroids.web.dto.Asteroid;
import com.morales.asteroids.web.services.mapper.NasaResponseToAsteroid;
import com.morales.nasa.client.models.NasaAsteroid;
import com.morales.nasa.integrations.GetNasaOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NasaService {

    @Value("${clients.nasa.credentials.api_key}")
    private String apiKey;
    
    @Autowired
    private GetNasaOperation getNasaOperation;

    @Autowired
    private NasaResponseToAsteroid mapper;

    public NasaService(GetNasaOperation getNasaOperation, NasaResponseToAsteroid mapper) {
        this.getNasaOperation = getNasaOperation;
    }

    public List<Asteroid> getAsteroids(int days) {
        log.info("getAsteroids with days {}", days);
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(days);
        try {   
            List<NasaAsteroid> objects = getNasaOperation.feed(startDate.toString(), endDate.toString());

            return objects.stream()
            .filter(NasaAsteroid::isPotentiallyHazardousAsteroid)
            .map(mapper::toAsteroid)
            .sorted((a, b) -> Float.compare(b.getDiameter(), a.getDiameter()))
            .limit(3)
            .collect(Collectors.toList());
            
        } catch (FeignClientException fe) {
            log.error("Exception fetching data from feed() method with start date {} and end date {}", startDate, endDate, fe);
            return Collections.emptyList();
        }    
    }

}
