package com.foodtruck.service;

import com.foodtruck.domainobjects.FoodTruckPermit;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface FoodService {

    List<FoodTruckPermit> searchByOwnerName(String ownerName);

    List<FoodTruckPermit> searchByExpiryDate(Date expiryTime);

    List<FoodTruckPermit> searchByStreetName(String streetName);

    String addFoodTruck(FoodTruckPermit foodTruck);

    String deleteFoodTruck(Long foodTruckId);

}
