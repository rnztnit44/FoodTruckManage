package com.foodtruck.service.impl;

import com.foodtruck.constant.FoodConstant;
import com.foodtruck.entity.FoodTruckPermit;
import com.foodtruck.repository.FoodTruckPermitRepository;
import com.foodtruck.service.FoodSchedulerSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FoodSchedulerSeviceImpl implements FoodSchedulerSevice {

    @Autowired
    private FoodTruckPermitRepository foodTruckPermitRepository;

    @Override
    public void expireFoodTrucksLicense() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        List<FoodTruckPermit> foodTruckList = foodTruckPermitRepository.findExpiredFoodTrucks(currentDate);
        for (FoodTruckPermit foodTruck : foodTruckList) {
            foodTruck.setStatus(FoodConstant.TRUCK_EXPIRED);
            foodTruckPermitRepository.save(foodTruck);
        }
    }
}
