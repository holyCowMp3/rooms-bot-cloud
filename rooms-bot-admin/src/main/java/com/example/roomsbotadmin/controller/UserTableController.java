package com.example.roomsbotadmin.controller;

import com.example.roomsbotadmin.models.User;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/user")
public class UserTableController {

    private LoadBalancerClient loadBalancerClient;
    private RestTemplate restTemplate;

    @GetMapping("/table")
    public String getUserTable(Model model) {
        model.addAttribute("userTelegram", restTemplate.getForObject(getBaseUrl() + "/api/user", User[].class));


        return "user-table";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        restTemplate.delete(getBaseUrl() + "/api/user/delete/" + id);

        return "redirect:/user/table";
    }

    private String getBaseUrl() {
        ServiceInstance instance = loadBalancerClient.choose("rooms-api");
        return instance.getUri().toString();
    }
}
