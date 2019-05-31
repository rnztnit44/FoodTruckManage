package com.foodtruck.response;

import lombok.Data;

@Data
public class FoodApiResponse {

    private String message;
    private long timestamp;
    private int status;

    public FoodApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public FoodApiResponse(int status) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
    }

    public FoodApiResponse(int status, String message) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.message = message;
    }

}
