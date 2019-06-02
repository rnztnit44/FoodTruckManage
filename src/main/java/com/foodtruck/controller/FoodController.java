package com.foodtruck.controller;

import com.foodtruck.bean.FoodTruck;
import com.foodtruck.bean.FoodTruckAddress;
import com.foodtruck.constant.ApiConstant;
import com.foodtruck.entity.FoodTruckPermit;
import com.foodtruck.exception.FoodTruckException;
import com.foodtruck.exception.LocationServiceException;
import com.foodtruck.response.FoodApiResponse;
import com.foodtruck.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.foodtruck.service.GoogleLocationService;

import javax.validation.constraints.NotNull;
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
    public ResponseEntity<List<FoodTruckPermit>> searchByOwnerName(@PathVariable("ownerName")@NotNull String ownerName) throws FoodTruckException {
        log.info("searchByOwnerName Api request params :{}", ownerName);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByOwnerName(ownerName));
    }

    @GetMapping("searchByExpiryDate/{expiryDate}")
    public ResponseEntity<List<FoodTruckPermit>> searchByExpiryDate(@PathVariable("expiryDate")@NotNull Date expiryDate) throws FoodTruckException {
        log.info("searchByExpiryDate Api request params :{}", expiryDate);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByExpiryDate(expiryDate));
    }

    @GetMapping("searchByStreet")
    public ResponseEntity<List<FoodTruckPermit>> searchByStreetName(@RequestParam @NotNull String streetName) throws FoodTruckException {
        log.info("searchByStreetName Api request params :{}", streetName);
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByStreetName(streetName));
    }

    @PostMapping("addTruck")
    public ResponseEntity<FoodApiResponse> addTruck(@RequestBody @NotNull FoodTruck foodTruck) throws FoodTruckException {
        log.info("addTruck Api request params :{}", foodTruck);
        String addSuccess = foodService.addFoodTruck(foodTruck);
        return ResponseEntity.ok().body(new FoodApiResponse(ApiConstant.SUCCESS_CODE,addSuccess));
    }

    @DeleteMapping("deleteTruck/{foodTruckId}")
    public ResponseEntity<FoodApiResponse> deleteTruck(@PathVariable @NotNull Long foodTruckId) throws FoodTruckException {
        log.info("deleteTruck Api request params :{}", foodTruckId);
        String deleteSuccess = foodService.deleteFoodTruck(foodTruckId);
        return ResponseEntity.ok().body(new FoodApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @DeleteMapping("nearestTruck")
    public ResponseEntity<FoodTruckPermit> getNearestTruck(@RequestParam(value = "destinationList") List<FoodTruckAddress> destinationList)
            throws LocationServiceException, InterruptedException {
        log.info("getNearestTruck Api request params :{}", destinationList);
        return ResponseEntity.status(HttpStatus.OK).body(googleLocationService.getNearestFoodTruck(destinationList));
    }
}