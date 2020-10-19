package com.foodtruck.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "food_truck_owner")
@Data
public class FoodTruckOwner {

    @Id
    @Column(name = "applicant_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int applicantId;

    @Column(name = "applicant_name",length = 50)
    private String applicantName;
}
