package com.driver.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity

public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    int adminId;
    String userName;
    String password;

}