package com.driver.Exceptions;

public class CustomerNotPresent extends RuntimeException{
    public CustomerNotPresent(String msg){
        super(msg);
    }
}