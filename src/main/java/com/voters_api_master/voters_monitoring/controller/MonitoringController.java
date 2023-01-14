package com.voters_api_master.voters_monitoring.controller;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController

@CrossOrigin("*")
public class MonitoringController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "monitorVoter/{id}")
    public String monitorVoter(@PathVariable("id") Long id) {
        String registeredvoters_data = "";
        String getVoters_data = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String registereddata_url = String.format("http://localhost:8089/getRegisterDataById/%x", id);
        String getvoters_url = String.format("http://localhost:8088/getVotersData/%x", id);
        registeredvoters_data = restTemplate.exchange(registereddata_url, HttpMethod.GET, entity, String.class).getBody();
        getVoters_data = restTemplate.exchange(getvoters_url, HttpMethod.GET, entity, String.class).getBody();
        return (registeredvoters_data + " " + getVoters_data);
    }
}

