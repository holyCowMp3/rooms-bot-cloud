package com.example.roomsbotadmin.controller;

import com.example.roomsbotadmin.models.User;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AjaxController {

    private RestTemplate restTemplate;
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return getUsersApi();
    }

    @GetMapping("/getDateUsers")
    public List<String> getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return getUsersApi().stream().map(x -> LocalDate.parse(format.format(x.getCreationDate())).toString()).collect(Collectors.toList());
    }


    private String getBaseUrl() {
        ServiceInstance instance = loadBalancerClient.choose("rooms-api");
        return instance.getUri().toString();
    }

    private List<User> getUsersApi() {
        return Arrays.stream(restTemplate.getForObject(getBaseUrl() + "/api/user", User[].class)).collect(Collectors.toList());
    }
}
