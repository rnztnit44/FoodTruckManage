package com.foodtruck.constant;

public class ExceptionConstant {
    private ExceptionConstant() {

    }
    public static final String NO_TRUCK_FOR_OWNER = "There is no FoodTruck for provided ownerName";
    public static final String NO_TRUCK_FOR_EXPIRY_DATE = "There is no FoodTruck whose permits have expired for provided expiryDate";
    public static final String NO_TRUCK_FOR_STREET = "There is no FoodTruck for provided streetName";
    public static final String UNABLE_ADD_TRUCK = "Unable to add Foodtruck into database";
    public static final String UNABLE_DELETE_TRUCK_FOR_TRUCKID = "Unable to delete Foodtruck for provided foodTruckId";
}
