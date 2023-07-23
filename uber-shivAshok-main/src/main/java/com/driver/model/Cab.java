package com.driver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
//@Table(name="cabs_table")
public class Cab {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int Id;
    int perKmRate;
    boolean available;

    @OneToOne

    @JoinColumn(name="drivid")
    Driver driver;
}
