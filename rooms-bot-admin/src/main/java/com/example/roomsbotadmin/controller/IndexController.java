package com.example.roomsbotadmin.controller;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@AllArgsConstructor
@CrossOrigin
public class IndexController {

    private LoadBalancerClient loadBalancerClient;
    private RestTemplate restTemplate;

    @GetMapping(value = "/")
    public String getIndex(Model model) {

//        System.out.println(getBaseUrl());
//        String r = restTemplate.getForObject(getBaseUrl() + "/api/apartments/get", String.class);
//        model.addAttribute("str", r);
        return "index";
    }

    private String getBaseUrl() {
        ServiceInstance instance = loadBalancerClient.choose("rooms-api");
        return instance.getUri().toString();
    }



}
