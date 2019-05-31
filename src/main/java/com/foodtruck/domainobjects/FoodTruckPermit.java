package com.foodtruck.domainobjects;

import lombok.Data;

import java.util.Date;

@Data
public class FoodTruckPermit {
    private int applicantId;
    private long locationId;
    private String permitNumber;
    private int facilityTypeId;
    private String status;
    private String foodItems;
    private String schedule;
    private Date noiSent;
    private Date approved;
    private int priorPermit;
    private Date expirationDate;
}
