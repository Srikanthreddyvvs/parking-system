package com.practice.Emp.Exception;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String msg){
        super(msg);
    }
}
