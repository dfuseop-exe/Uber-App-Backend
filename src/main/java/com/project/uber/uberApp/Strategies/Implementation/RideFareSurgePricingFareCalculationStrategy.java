package com.project.uber.uberApp.Strategies.Implementation;

import com.project.uber.uberApp.Strategies.RideFareCalculationStrategy;
import com.project.uber.uberApp.entities.RideRequest;
import com.project.uber.uberApp.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService ;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation() , rideRequest.getDropOffLocation()) ;
        double SURGE_FACTOR = 2.0;
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}
