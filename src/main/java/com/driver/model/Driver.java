package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
//@Table(name="driverstable")
public class Driver {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;

    String mobile;
    String password;

    public Driver() {
    }

    public Driver(int id, String mobile, String password, List<TripBooking> tripList, Cab cab) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.tripList = tripList;
        this.cab = cab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TripBooking> getTripList() {
        return tripList;
    }

    public void setTripList(List<TripBooking> tripList) {
        this.tripList = tripList;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    @OneToMany(mappedBy = "driver",cascade =CascadeType.ALL)
    List<TripBooking> tripList=new ArrayList<>();

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    Cab cab;
}