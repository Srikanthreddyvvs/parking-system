package com.practice.Emp.Exception;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String msg){
        super(msg);
    }
}
