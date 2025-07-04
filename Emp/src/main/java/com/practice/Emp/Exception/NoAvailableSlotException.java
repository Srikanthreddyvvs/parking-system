package com.practice.Emp.Exception;

public class NoAvailableSlotException extends RuntimeException{
    public NoAvailableSlotException(String msg){
        super(msg);
    }
}
