package com.project.uber.uberApp.services;

import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.RideDTO;
import com.project.uber.uberApp.dto.RideStartDTO;
import com.project.uber.uberApp.dto.RiderDTO;
import com.project.uber.uberApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {
    RideDTO acceptRide(Long rideRequestId);

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId, RideStartDTO rideStartDTO);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    Page<RideDTO> getMyAllRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver , boolean availability);

    Driver updateDriver(Driver driver);

    Driver createNewDriver(Driver driver);
}
