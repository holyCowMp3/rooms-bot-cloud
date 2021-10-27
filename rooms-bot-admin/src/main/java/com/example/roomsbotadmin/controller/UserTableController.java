package com.example.roomsbotadmin.controller;

import com.example.roomsbotadmin.models.User;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@AllArgsConstructor
public class UserTableController {

    private LoadBalancerClient loadBalancerClient;
    private RestTemplate restTemplate;

    @GetMapping("/user-table")
    public String getUserTable(Model model) {
        System.out.println(getBaseUrl());
        model.addAttribute("userTelegram", restTemplate.getForObject(getBaseUrl() + "/api/user", User[].class));


        return "user-table";
    }

    private String getBaseUrl() {
        ServiceInstance instance = loadBalancerClient.choose("SPRING-SECURITY-KEYCLOAK");
        return instance.getUri().toString();
    }
}
