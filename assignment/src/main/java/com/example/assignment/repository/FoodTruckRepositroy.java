package com.example.assignment.repository;

import com.example.assignment.api.response.FoodTruck;
import org.springframework.data.repository.CrudRepository;

public interface FoodTruckRepositroy extends CrudRepository<FoodTruck, Integer> {
}
