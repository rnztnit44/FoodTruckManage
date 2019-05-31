package com.foodtruck.scheduler;
import com.foodtruck.service.FoodSchedulerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FoodTruckExpiryScheduler {
    @Autowired
    private FoodSchedulerSevice foodSchedulerSevice;

    private static final long EXPIRYINTERVAL = 86400000 ;//Milliseconds equivalent to 1 Day

    @Scheduled(fixedRate = EXPIRYINTERVAL)
    private void expireLicenseTrucks() {
        foodSchedulerSevice.expireFoodTrucksLicense();
    }
}
