package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
//@Table(name="tripstable")
public class TripBooking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String fromLocation;
    String toLocation;

    int distanceInkm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getDistanceInkm() {
        return distanceInkm;
    }

    public void setDistanceInkm(int distanceInkm) {
        this.distanceInkm = distanceInkm;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Enumerated(value=EnumType.STRING)
    TripStatus status;
    int bill;

    // we should give the coulum name of child class manually if child is having more than 1 foreign key otherwise if
    //we would not give a nested loop be created because it would give same name to both the table
    //and their parents should also have diff names;
    @ManyToOne
    @JoinColumn//(name="driid")
            Driver driver;

    @ManyToOne
    @JoinColumn//(name="custid")
    Customer customer;


}