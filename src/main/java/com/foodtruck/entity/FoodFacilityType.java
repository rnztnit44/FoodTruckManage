package com.foodtruck.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_facility_type")
@Data
public class FoodFacilityType {

    @Id
    @Column(name = "facility_type_id")
    private int facilityTypeId;

    @Column(name = "facility_type")
    private String facilityType;
}
