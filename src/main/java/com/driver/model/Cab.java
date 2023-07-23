package com.driver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;



@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
//@Table(name="cabs_table")
public class Cab {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int Id;
    int perKmRate;
    boolean available;

    public Cab(int id, int perKmRate, boolean available, Driver driver) {
        Id = id;
        this.perKmRate = perKmRate;
        this.available = available;
        this.driver = driver;
    }

    public Cab() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @OneToOne
    @JoinColumn(name="drivid")
    Driver driver;
}