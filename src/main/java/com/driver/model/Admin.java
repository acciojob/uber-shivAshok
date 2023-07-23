package com.driver.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;




@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity

public class Admin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    int adminId;
    String userName;
    String password;

    public String getUserName() {
        return userName;
    }

    public Admin() {
    }

    public Admin(int adminId, String userName, String password) {
        this.adminId = adminId;
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}