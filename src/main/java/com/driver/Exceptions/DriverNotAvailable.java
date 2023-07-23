package com.driver.Exceptions;

public class DriverNotAvailable extends RuntimeException{
    public DriverNotAvailable(String msg){
        super(msg);
    }
}