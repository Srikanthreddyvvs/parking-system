package com.practice.Emp.EmpService;

import com.practice.Emp.Entity.VisitorLog;

public interface VisitorService {
    VisitorLog enter(String vehicleNumber,String email);
    VisitorLog exit(String vehicleNumber);
    VisitorLog search(String vehicleNumber);
}
