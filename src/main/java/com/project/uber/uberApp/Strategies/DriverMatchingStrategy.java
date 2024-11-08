package com.project.uber.uberApp.Strategies;

import com.project.uber.uberApp.entities.Driver;
import com.project.uber.uberApp.entities.RideRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest) ;
}
