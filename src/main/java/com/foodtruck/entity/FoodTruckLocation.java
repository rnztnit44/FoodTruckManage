package com.foodtruck.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "food_truck_location")
@Data
public class FoodTruckLocation {

    @Id
    @Column(name = "location_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long locationId;

    @Column(name = "cnn")
    private long cnn;

    @Column(name = "location_description")
    private String locationDescription;

    @Column(name = "address" , length = 80)
    private String address;

    @Column(name = "block_lot")
    private String blocklot;

    @Column(name = "block")
    private String block;

    @Column(name = "lot")
    private String lot;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
