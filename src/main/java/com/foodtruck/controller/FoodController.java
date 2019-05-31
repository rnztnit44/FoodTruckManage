package com.foodtruck.controller;

import com.foodtruck.bean.FoodTruckAddress;
import com.foodtruck.domainobjects.FoodTruckPermit;
import com.foodtruck.exception.FoodTruckException;
import com.foodtruck.exception.LocationServiceException;
import com.foodtruck.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.foodtruck.service.GoogleLocationService;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/foodTruck/")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private GoogleLocationService googleLocationService;

    @GetMapping("searchByOwner/{ownerName}")
    public ResponseEntity<List<FoodTruckPermit>> searchByOwnerName(@PathVariable("name") String ownerName) throws FoodTruckException {
        log.info("searchByOwnerName Api request params :{}", ownerName);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByOwnerName(ownerName));
    }

    @GetMapping("searchByExpiryDate/{expiryDate}")
    public ResponseEntity<List<FoodTruckPermit>> searchByExpiryDate(@PathVariable("expiryDate") Date expiryDate) throws FoodTruckException {
        log.info("searchByExpiryDate Api request params :{}", expiryDate);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByExpiryDate(expiryDate));
    }

    @GetMapping("searchByStreet")
    public ResponseEntity<List<FoodTruckPermit>> searchByStreetName(@RequestParam String streetName) throws FoodTruckException {
        log.info("searchByStreetName Api request params :{}", streetName);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByStreetName(streetName));
    }

    @PostMapping("addTruck")
    public ResponseEntity<String> addTruck(@RequestBody FoodTruckPermit foodTruck) {
        log.info("addTruck Api request params :{}", foodTruck);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.addFoodTruck(foodTruck));
    }

    @DeleteMapping("deleteTruck/{foodTruckId}")
    public ResponseEntity<String> deleteTruck(@PathVariable Long foodTruckId) {
        log.info("deleteTruck Api request params :{}", foodTruckId);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.deleteFoodTruck(foodTruckId));
    }

    @DeleteMapping("nearestTruck")
    public ResponseEntity<FoodTruckPermit> getNearestTruck(@RequestParam(value = "destinationList") List<FoodTruckAddress> destinationList) throws LocationServiceException, InterruptedException {
        log.info("getNearestTruck Api request params :{}", destinationList);
        return ResponseEntity.status(HttpStatus.OK).body(googleLocationService.getNearestFoodTruck(destinationList));
    }
}