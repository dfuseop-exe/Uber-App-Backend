package com.project.uber.uberApp.controllers;

import com.project.uber.uberApp.dto.RideDTO;
import com.project.uber.uberApp.dto.RideStartDTO;
import com.project.uber.uberApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequest_id}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequest_id) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequest_id)) ;
    }

    @PostMapping("/startRide/{rideRequest_id}")
    public ResponseEntity<RideDTO> startRide(@PathVariable Long rideRequest_id , @RequestBody RideStartDTO rideStartDTO) {
        return ResponseEntity.ok(driverService.startRide(rideRequest_id , rideStartDTO));
    }
}
