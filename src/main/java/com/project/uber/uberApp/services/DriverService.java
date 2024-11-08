package com.project.uber.uberApp.services;

import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.RideDTO;
import com.project.uber.uberApp.dto.RideStartDTO;
import com.project.uber.uberApp.dto.RiderDTO;
import com.project.uber.uberApp.entities.Driver;

import java.util.List;

public interface DriverService {
    RideDTO acceptRide(Long rideRequestId) ;
    RideDTO cancelRide(Long rideId) ;
    RideDTO startRide(Long rideId , RideStartDTO rideStartDTO) ;
    RideDTO endRide(Long rideId) ;
    RiderDTO rateRider(Long rideId , Integer rating) ;
    DriverDTO getMyProfile() ;
    List<RideDTO> getMyAllRides() ;
    Driver getCurrentDriver() ;
}
