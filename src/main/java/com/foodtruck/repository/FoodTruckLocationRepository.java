package com.foodtruck.repository;

import com.foodtruck.entity.FoodTruckLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodTruckLocationRepository extends JpaRepository<FoodTruckLocation, Long> {
    @Query(value = "Select address from food_truck_location", nativeQuery = true)
    List<String> findAllFoodTrucks();
}
