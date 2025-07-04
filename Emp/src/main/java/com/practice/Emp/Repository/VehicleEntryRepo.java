package com.practice.Emp.Repository;

import com.practice.Emp.Entity.VisitorLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleEntryRepo extends JpaRepository<VisitorLog,Long> {
    Optional<VisitorLog> findByVehicleNumberAndExitTimeIsNull(String vehicleNumber);

    Optional<VisitorLog> findByQrCodeData(String qrCodeData);

}
