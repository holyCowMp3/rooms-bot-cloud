package com.example.roomsbotadmin.controller;

import com.example.roomsbotadmin.models.User;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
@CrossOrigin
public class IndexController {

    private LoadBalancerClient loadBalancerClient;
    private RestTemplate restTemplate;

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        List<User> users = Arrays.stream(restTemplate.getForObject(getBaseUrl() + "/api/user", User[].class)).collect(Collectors.toList());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int forDay = (int) users.stream().filter(x -> LocalDate.now().dayOfYear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).dayOfYear())).count();
        int forWeek = (int) users.stream().filter(x -> LocalDate.now().weekyear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).weekyear())).count();
        int forMonth = (int) users.stream().filter(x -> LocalDate.now().monthOfYear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).monthOfYear())).count();

//        java.time.LocalDate localDate = java.time.LocalDate.of(2020, 11, 29);
//
//        System.out.println("days " + LocalDate.parse(localDate.toString()).dayOfYear().equals(LocalDate.now().dayOfYear()));
//        System.out.println("week " + LocalDate.parse(localDate.toString()).weekOfWeekyear().equals(LocalDate.now().weekOfWeekyear()));
//        System.out.println("month " + LocalDate.parse(localDate.toString()).monthOfYear().equals(LocalDate.now().monthOfYear()));

        model.addAttribute("allUsers", users.size());
        model.addAttribute("forDay", forDay);
        model.addAttribute("forWeek", forWeek);
        model.addAttribute("forMonth", forMonth);

        return "index";
    }

    private String getBaseUrl() {
        ServiceInstance instance = loadBalancerClient.choose("rooms-api");
        return instance.getUri().toString();
    }

}
