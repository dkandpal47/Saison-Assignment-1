package com.example.assignment.service;

import com.example.assignment.api.model.Location;
import com.example.assignment.api.response.FoodTruck;
import com.example.assignment.repository.FoodTruckRepositroy;
import com.example.assignment.util.TruckUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FoodTruckService {

    @Autowired
    FoodTruckRepositroy foodTruckRepositroy;

    public FoodTruck save (FoodTruck ft){
       FoodTruck foodTruckSaved = foodTruckRepositroy.save(ft);
       return foodTruckSaved;
    }

    public FoodTruck findnearest(List<FoodTruck> truckList, Location givenLocation) {
        Map<Double, Double> distanceMap = new LinkedHashMap<>();

        // Populating distance map
        truckList.stream().forEach(foodTruck -> distanceMap.put(foodTruck.getLocationId(),
                TruckUtil.distance(foodTruck.getLatitude(), foodTruck.getLongitude(),givenLocation.getLatitude(),
                        givenLocation.getLongitude(), "K")));

        Double min = Double.MAX_VALUE;
        Double minLocation = Double.MAX_VALUE;
        for (Double key : distanceMap.keySet()){

            Double tmp = distanceMap.get(key);
            if( tmp < min){
                min = tmp;
                minLocation = key;
            }
        }

        final Double filterlocationId = minLocation;
        return  truckList.stream().filter( truck -> truck.getLocationId() == filterlocationId).collect(Collectors.toList()).get(0);

    }
}
