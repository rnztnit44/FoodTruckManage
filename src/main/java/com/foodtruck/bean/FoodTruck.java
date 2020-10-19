package com.foodtruck.bean;

import lombok.Data;

import java.util.Date;

@Data
public class FoodTruck {
    private int applicantId;
    private long locationId;
    private String permitNumber;
    private int facilityTypeId;
    private String foodItems;
    private int priorPermit;
    private Date expirationDate;
}
