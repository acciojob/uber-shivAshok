package com.driver.Exceptions;

public class AdminNotpresent extends RuntimeException{
    AdminNotpresent(String msg){
        super(msg);
    }
}