package com.example.assignment.controller;

import com.example.assignment.api.model.Location;
import com.example.assignment.api.response.FoodTruck;
import com.example.assignment.api.response.FoodTruckResponse;
import com.example.assignment.service.FoodTruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/TruckService/")
public class TruckController {

    @Autowired
    FoodTruckService foodTruckService;

    final String uri = "https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat";

    private FoodTruckResponse getFoodTruckResponse(){
        RestTemplate restTemplate = new RestTemplate();
        FoodTruckResponse result = restTemplate.getForObject(uri, FoodTruckResponse.class);
        return result;
    }

    @GetMapping("filterByName/{name}")
    public FoodTruckResponse filterByApplicantName (@PathVariable("name") String applicantName){
        FoodTruckResponse foodTruckResponse = getFoodTruckResponse();
        foodTruckResponse.getFoodTruckResponse().stream()
                .filter(applicant -> applicant.getApplicant().equalsIgnoreCase(applicantName))
                .collect(Collectors.toList());

        return foodTruckResponse;
    }

    @GetMapping("expired")
    public FoodTruckResponse filterForExpiredPermits (){
        FoodTruckResponse foodTruckResponse = getFoodTruckResponse();
        Date today = new Date();
        foodTruckResponse.getFoodTruckResponse().stream()
                .filter(applicant -> applicant.getExpirationDate().compareTo(today) < 0)
                .collect(Collectors.toList());

        return foodTruckResponse;
    }

    @GetMapping("streetName/{streetName}")
    public FoodTruckResponse filterForExpiredPermits (@PathVariable("streetName") String streetName){
        FoodTruckResponse foodTruckResponse = getFoodTruckResponse();
        foodTruckResponse.getFoodTruckResponse().stream()
                .filter(applicant -> applicant.getLocationDescription().contains(streetName))
                .collect(Collectors.toList());

        return foodTruckResponse;
    }

    @PutMapping("truck/save")
    public FoodTruck saveFoodTruckInformation(@RequestBody FoodTruck foodTruck){
        FoodTruck saved = foodTruckService.save(foodTruck);
        return saved;
    }

    @GetMapping("closest/{location}")
    public FoodTruck nearestTruck(@PathVariable("locaiton") Location givenLocation){
        FoodTruckResponse foodTruckResponse = getFoodTruckResponse();
        return foodTruckService.findnearest(foodTruckResponse.getFoodTruckResponse(), givenLocation);
    }

}
