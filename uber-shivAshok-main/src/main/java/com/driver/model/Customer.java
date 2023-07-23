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
//@Table(name="custumer_table")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String mobileno;
    String password;

    @OneToMany(mappedBy ="customer",cascade = CascadeType.ALL)

    List<TripBooking> customerTrips=new ArrayList<>();

}
