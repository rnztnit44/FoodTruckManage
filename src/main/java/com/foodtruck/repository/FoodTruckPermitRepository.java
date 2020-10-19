package com.foodtruck.repository;

import com.foodtruck.entity.FoodTruckPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FoodTruckPermitRepository extends JpaRepository<FoodTruckPermit, Long> {

    @Query(value = "Select * from food_truck_permit P Inner join food_truck_owner O"
            +" ON P.applicant_id = O.applicant_id where O.applicant_name=:ownerName", nativeQuery = true)
    List<FoodTruckPermit> findByOwnerName(String ownerName);

    @Query(value = "Select * from food_truck_permit where expiration_date=:expirationDate", nativeQuery = true)
    List<FoodTruckPermit> findByExpirationDate(Date expirationDate);

    @Query(value = "Select * from food_truck_permit P Inner join food_truck_location L"
            + " ON P.location_id = L.location_id where L.address=:streetName", nativeQuery = true)
    List<FoodTruckPermit> findByStreetName(String streetName);

    @Query(value = "Select * from food_truck_permit where expiration_date < :currentDate", nativeQuery = true)
    List<FoodTruckPermit> findExpiredFoodTrucks(Date currentDate);

    @Query(value = "Select * from food_truck_permit P Inner join food_truck_location L"
            + " ON P.location_id = L.location_id where L.address=:address", nativeQuery = true)
    FoodTruckPermit findFoodTrucksByAddress(String address);
}
