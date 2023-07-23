package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Getter
@Setter
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
