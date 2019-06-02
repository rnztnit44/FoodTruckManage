package com.foodtruck.domainobjects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_truck_owner")
@Data
public class FoodTruckOwner {

    @Id
    @Column(name = "applicant_id")
    private int applicantId;

    @Column(name = "applicant_name",length = 50)
    private String applicantName;
}
