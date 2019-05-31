package com.foodtruck.domainobjects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "food_facility_type")
@Data
public class FoodFacilityType {
    @Column(name = "facility_type_id")
    private int facilityTypeId;
    @Column(name = "facility_type")
    private String facilityType;
}
