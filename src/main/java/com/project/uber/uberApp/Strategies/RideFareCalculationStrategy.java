package com.project.uber.uberApp.Strategies;

import com.project.uber.uberApp.entities.RideRequest;
import org.springframework.stereotype.Component;

@Component
public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
