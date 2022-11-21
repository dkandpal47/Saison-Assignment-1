package com.example.assignment.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodTruckResponse {
    List<FoodTruck> foodTruckResponse;
}
