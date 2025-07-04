package com.practice.Emp.Controller;

import com.practice.Emp.EmpService.Service;
import com.practice.Emp.Entity.VisitorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class Controller {
    @Autowired
    private Service service;

    @PostMapping("/enter")
    public ResponseEntity<VisitorLog> enter(@RequestBody VisitorLog vis){
        VisitorLog log = service.enter(vis.getVehicleNumber(),vis.getEmail());
        return new ResponseEntity<>(log, HttpStatus.CREATED);
    }
    @PutMapping("/exit")
    public ResponseEntity<VisitorLog> exit(@RequestParam String vehicleNumber){
        VisitorLog log = service.exit(vehicleNumber);
        return new ResponseEntity<>(log,HttpStatus.OK);
    }
    @GetMapping("/exit/qr")
    public ResponseEntity<VisitorLog> exitByQr(@RequestParam String qr){
        VisitorLog log = service.exitByQr(qr);
        return new ResponseEntity<>(log,HttpStatus.OK);
    }
}
