package com.foodtruck.service;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.domainobjects.FoodTruckPermit;
import com.foodtruck.exception.FoodTruckException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface FoodService {

    List<FoodTruckPermit> searchByOwnerName(String ownerName) throws FoodTruckException;

    List<FoodTruckPermit> searchByExpiryDate(Date expiryTime) throws FoodTruckException;

    List<FoodTruckPermit> searchByStreetName(String streetName) throws FoodTruckException;

    String addFoodTruck(FoodTruckPermit foodTruck);

    String deleteFoodTruck(Long foodTruckId);

}