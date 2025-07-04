package com.practice.Emp.EmpService;


import com.practice.Emp.Entity.ParkingSlot;
import com.practice.Emp.Entity.VisitorLog;
import com.practice.Emp.Exception.DuplicateVehicleEntryException;
import com.practice.Emp.Exception.InvalidExitOperationException;
import com.practice.Emp.Exception.NoAvailableSlotException;
import com.practice.Emp.Exception.VehicleNotFoundException;
import com.practice.Emp.Repository.ParkingSlotRepo;
import com.practice.Emp.Repository.VehicleEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private ParkingSlotRepo slotRepo;
    @Autowired
    private VehicleEntryRepo carRepo;
    @Autowired
    private Mail mail;

    public VisitorLog enter(String vehicleNumber,String email){
        Optional<VisitorLog> car = carRepo.findByVehicleNumberAndExitTimeIsNull(vehicleNumber);
        if(car.isPresent()){
            throw new DuplicateVehicleEntryException("Vehicle is already there");
        }
        List<ParkingSlot> slots = slotRepo.findByIsAvailableTrue();
        if(slots.isEmpty()){
            throw new NoAvailableSlotException("No slots are available");
        }
        ParkingSlot slot = slots.get(new Random().nextInt(slots.size()));
        slot.setAvailable(false);
        slotRepo.save(slot);
        VisitorLog visitor = new VisitorLog();
        visitor.setVehicleNumber(vehicleNumber);
        visitor.setEmail(email);
        visitor.setEntryTime(LocalDateTime.now());
        visitor.setSlot(slot);

        VisitorLog vis = carRepo.save(visitor);
        String qrText = "QR-" + vis.getId() + "-" + vehicleNumber;
        vis.setQrCodeData(qrText);
        vis = carRepo.save(vis);
        try{
            String redirectUrl = "http://localhost:8086/api/parking/exit/qr?qr=" + qrText;
            byte[] qrImage = QR.qr(redirectUrl,200,200);
            mail.sendQR(email,qrImage,slot.getSlotNumber());
        }catch(Exception e){
            System.out.println("failed to send: "+e.getMessage());
        }

        return vis;
    }

    public VisitorLog exit(String vehicleNumber){
        Optional<VisitorLog> car = carRepo.findByVehicleNumberAndExitTimeIsNull(vehicleNumber);
        if(car.isEmpty()){
            throw new VehicleNotFoundException("there is no vehicle with number "+vehicleNumber+" inside");
        }
        VisitorLog car1 = car.get();
        if(car1.getExitTime()!=null){
            throw new InvalidExitOperationException("Vehicle has already exited");
        }
        car1.setExitTime(LocalDateTime.now());
        ParkingSlot slot = car1.getSlot();
        slot.setAvailable(true);
        slotRepo.save(slot);
        return carRepo.save(car1);
    }
    public VisitorLog exitByQr(String qr){
        Optional<VisitorLog> opt = carRepo.findByQrCodeData(qr);
        if(opt.isEmpty()){
            throw new VehicleNotFoundException("Invalid Qr code.No vehicle found");
        }
        VisitorLog vis =opt.get();
        if(vis.getExitTime()!=null){
            throw new InvalidExitOperationException("Vehicle has already exited");
        }
        vis.setExitTime(LocalDateTime.now());
        ParkingSlot slot = vis.getSlot();
        slot.setAvailable(true);
        slotRepo.save(slot);
        return carRepo.save(vis);
    }
}
