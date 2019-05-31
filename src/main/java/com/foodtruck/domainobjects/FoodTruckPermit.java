package com.foodtruck.domainobjects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "food_truck_permit")
@Data
public class FoodTruckPermit {
    @Column(name = "applicant_id")
    private int applicantId;
    @Column(name = "location_id")
    private long locationId;
    @Column(name = "permit_number")
    private String permitNumber;
    @Column(name = "facility_type_id")
    private int facilityTypeId;
    @Column(name = "status")
    private String status;
    @Column(name = "food_items")
    private String foodItems;
    @Column(name = "schedule")
    private String schedule;
    @Column(name = "noi_sent")
    private Date noiSent;
    @Column(name = "approved")
    private Date approved;
    @Column(name = "prior_permit")
    private int priorPermit;
    @Column(name = "expiration_date")
    private Date expirationDate;

}
