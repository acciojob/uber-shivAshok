package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
//@Table(name="driverstable")
public class Driver {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;

    String mobile;
    String password;

    @OneToMany(mappedBy = "driver",cascade =CascadeType.ALL)
    List<TripBooking> tripList=new ArrayList<>();

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    Cab cab;
}
