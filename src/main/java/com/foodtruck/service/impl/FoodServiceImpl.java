package com.foodtruck.service.impl;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.constant.ExceptionConstant;
import com.foodtruck.constant.FoodConstant;
import com.foodtruck.entity.FoodTruckPermit;
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
        if(foodTruckList.isEmpty()) {
            throw new FoodTruckException(ExceptionConstant.NO_TRUCK_FOR_OWNER);
        }
        return foodTruckList;
    }

    @Override
    public List<FoodTruckPermit> searchByExpiryDate(Date expiryDate) throws FoodTruckException{
        List<FoodTruckPermit> foodTruckList = foodTruckPermitRepository.findByExpirationDate(expiryDate);
        if(foodTruckList.isEmpty()) {
            throw new FoodTruckException(ExceptionConstant.NO_TRUCK_FOR_EXPIRY_DATE);
        }
        return foodTruckList;
    }

    @Override
    public List<FoodTruckPermit> searchByStreetName(String streetName) throws FoodTruckException {
        List<FoodTruckPermit> foodTruckList = foodTruckPermitRepository.findByStreetName(streetName);
        if(foodTruckList.isEmpty()) {
            throw new FoodTruckException(ExceptionConstant.NO_TRUCK_FOR_STREET);
        }
        return foodTruckList;
    }

    @Override
    public String addFoodTruck(FoodTruck foodTruck) throws FoodTruckException{
        try {
            FoodTruckPermit foodTruckPermit = FoodTruckPermit.builder().applicantId(foodTruck.getApplicantId()).locationId(foodTruck.getLocationId())
                    .permitNumber(foodTruck.getPermitNumber()).facilityTypeId(foodTruck.getFacilityTypeId())
                    .foodItems(foodTruck.getFoodItems()).priorPermit(foodTruck.getPriorPermit())
                    .expirationDate(foodTruck.getExpirationDate()).build();
            foodTruckPermitRepository.save(foodTruckPermit);
        }catch(Exception e) {
            log.error(ExceptionConstant.UNABLE_ADD_TRUCK + " {}", e.getMessage());
            throw new FoodTruckException(ExceptionConstant.UNABLE_ADD_TRUCK);
        }
        return FoodConstant.ADD_SUCCESS;
    }

    @Override
    public String deleteFoodTruck(Long foodTruckId) throws FoodTruckException{
        try {
            foodTruckPermitRepository.deleteById(foodTruckId);
        }catch(Exception e) {
            log.error(ExceptionConstant.UNABLE_DELETE_TRUCK_FOR_TRUCKID + " {}", foodTruckId);
            throw new FoodTruckException(ExceptionConstant.UNABLE_DELETE_TRUCK_FOR_TRUCKID);
        }
        return FoodConstant.DELETE_SUCCESS;
    }
}