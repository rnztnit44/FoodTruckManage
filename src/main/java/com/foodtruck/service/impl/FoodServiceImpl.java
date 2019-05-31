package com.foodtruck.service.impl;

import com.foodtruck.domainobjects.FoodTruckPermit;
import com.foodtruck.exception.FoodTruckException;
import com.foodtruck.repository.FoodTruckPermitRepository;
import com.foodtruck.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodTruckPermitRepository foodTruckPermitRepository;

    @Override
    public List<FoodTruckPermit> searchByOwnerName(String ownerName) throws FoodTruckException{
        List<FoodTruckPermit> foodTruckList = foodTruckPermitRepository.findByOwnerName(ownerName);
        if(foodTruckList.size() == 0 ) {
            throw new FoodTruckException("There is no FoodTruck for provided ownername");
        }
        return foodTruckList;
    }

    @Override
    public List<FoodTruckPermit> searchByExpiryDate(Date expiryDate) throws FoodTruckException{
        List<FoodTruckPermit> foodTruckList = foodTruckPermitRepository.findByExpirationDate(expiryDate);
        if(foodTruckList.size() == 0 ) {
            throw new FoodTruckException("There is no FoodTruck whose permits have expired for provided expiryDate");
        }
        return foodTruckList;
    }

    @Override
    public List<FoodTruckPermit> searchByStreetName(String streetName) throws FoodTruckException {
        List<FoodTruckPermit> foodTruckList = foodTruckPermitRepository.findByStreetName(streetName);
        if(foodTruckList.size() == 0 ) {
            throw new FoodTruckException("There is no FoodTruck for provided streetName");
        }
        return foodTruckList;
    }

    @Override
    public String addFoodTruck(FoodTruckPermit foodTruck) {
        foodTruckPermitRepository.save(foodTruck);
        return "Food truck added successfully";
    }

    @Override
    public String deleteFoodTruck(Long foodTruckId) {
        foodTruckPermitRepository.deleteById(foodTruckId);
        return "Food truck deleted successfully";
    }
}