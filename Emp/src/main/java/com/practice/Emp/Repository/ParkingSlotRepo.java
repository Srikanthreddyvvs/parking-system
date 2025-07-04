package com.practice.Emp.Repository;

import com.practice.Emp.Entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepo extends JpaRepository<ParkingSlot,Long> {
    List<ParkingSlot> findByIsAvailableTrue();
}
