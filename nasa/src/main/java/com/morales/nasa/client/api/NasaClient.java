package com.morales.nasa.client.api;

import com.morales.nasa.client.models.NasaResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nasa", url="https://api.nasa.gov/neo/rest/v1")
public interface NasaClient {
    @RequestMapping(method = RequestMethod.GET, value = "/feed")
    NasaResponse feed(@RequestParam("api_key") String apiKey, @RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate);
}
