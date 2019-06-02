package com.foodtruck.response;

import lombok.Data;

@Data
public class FoodApiResponse {

    private String message;
    private int status;

    public FoodApiResponse() {
    }

    public FoodApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
