package com.morales.nasa.integrations;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.morales.nasa.client.api.NasaClient;
import com.morales.nasa.client.models.NasaAsteroid;
import com.morales.nasa.client.models.NasaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetNasaOperation {

    @Value("${clients.nasa.credentials.api_key}")
    private String apiKey;

    @Autowired
    private NasaClient nasaClient;

    public GetNasaOperation(NasaClient nasaClient) {
        this.nasaClient = nasaClient;
    }

    public List<NasaAsteroid> feed(String startDate, String endDate) throws FeignClientException {
        log.info("feed", startDate, endDate);
        Optional<NasaResponse> response = Optional.ofNullable(nasaClient.feed(apiKey, startDate, endDate));
        List<NasaAsteroid> asteroids = Collections.emptyList();
        if (response.isPresent()) {
            Map<String, List<NasaAsteroid>> objects = response.get().getNearEarthObjects();
            if (!CollectionUtils.isEmpty(objects.values())) {
                asteroids = objects.values().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
            }
        }
        return asteroids;
    }

}
