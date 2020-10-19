package com.foodtruck.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodTruckAddress {
    private String addressLine;

    private String region;

    private String city;

    private String state;

    private String pincode;
}
