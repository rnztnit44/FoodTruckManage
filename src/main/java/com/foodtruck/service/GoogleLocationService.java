package com.foodtruck.service;

import com.foodtruck.bean.FoodTruckAddress;
import com.foodtruck.entity.FoodTruckPermit;
import com.foodtruck.exception.LocationServiceException;

import java.util.List;

public interface GoogleLocationService {
    FoodTruckPermit getNearestFoodTruck(List<FoodTruckAddress> destinationList) throws LocationServiceException, InterruptedException;
}
