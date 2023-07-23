package com.driver.Exceptions;

public class TripNotExist extends RuntimeException{
   public TripNotExist(String msg){
        super(msg);
    }
}
