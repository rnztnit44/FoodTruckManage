package com.foodtruck.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "food_facility_type")
@Data
public class FoodFacilityType {

    @Id
    @Column(name = "facility_type_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int facilityTypeId;

    @Column(name = "facility_type")
    private String facilityType;
}
