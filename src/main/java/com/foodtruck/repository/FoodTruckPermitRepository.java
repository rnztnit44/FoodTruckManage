package com.foodtruck.repository;

import com.foodtruck.domainobjects.FoodTruckPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FoodTruckPermitRepository extends JpaRepository<FoodTruckPermit, Long> {

    @Query(value = "Select * from food_truck_permit where applicantId=:ownerName", nativeQuery = true)
    List<FoodTruckPermit> findByOwnerName(String ownerName);

    @Query(value = "Select * from food_truck_permit where expirationDate=:expirationDate", nativeQuery = true)
    List<FoodTruckPermit> findByExpirationDate(Date expirationDate);

    @Query(value = "Select * from food_truck_permit P Inner join food_truck_location L"
            + "ON P.locationId = L.locationId where streetName=:streetName", nativeQuery = true)
    List<FoodTruckPermit> findByStreetName(String streetName);

    @Query(value = "Select * from food_truck_permit where expirationDate < :currentDate", nativeQuery = true)
    List<FoodTruckPermit> findExpiredFoodTrucks(Date currentDate);

    @Query(value = "Select * from food_truck_permit P Inner join food_truck_location L"
            + "ON P.locationId = L.locationId where L.address=:address", nativeQuery = true)
    FoodTruckPermit findFoodTrucksByAddress(String address);
}
