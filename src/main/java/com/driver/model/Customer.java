package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
//@Table(name="custumer_table")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String mobileno;
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TripBooking> getCustomerTrips() {
        return customerTrips;
    }

    public void setCustomerTrips(List<TripBooking> customerTrips) {
        this.customerTrips = customerTrips;
    }

    @OneToMany(mappedBy ="customer",cascade = CascadeType.ALL)

    List<TripBooking> customerTrips=new ArrayList<>();

}