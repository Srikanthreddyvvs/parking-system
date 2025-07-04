package com.practice.Emp.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class VisitorLog{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Vehicle number is required")
    @Size(max = 20,message ="vehicle number should not have more than 20 characters")
    private String vehicleNumber;
    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email")
    private String email;

    @ManyToOne
    @JoinColumn(name="slot_id",nullable = false)
    private ParkingSlot slot;

    @PastOrPresent(message = "Entry time cannot be in future")
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String qrCodeData;

    public VisitorLog() {
    }

    public VisitorLog(Long id, String vehicleNumber, String email, ParkingSlot slot, LocalDateTime entryTime, LocalDateTime exitTime, String qrCodeData) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.email = email;
        this.slot = slot;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.qrCodeData = qrCodeData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }
}