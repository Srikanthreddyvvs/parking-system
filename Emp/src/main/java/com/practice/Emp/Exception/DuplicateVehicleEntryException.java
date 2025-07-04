package com.practice.Emp.Exception;

public class DuplicateVehicleEntryException extends RuntimeException{
    public DuplicateVehicleEntryException(String msg){
        super(msg);
    }
}
