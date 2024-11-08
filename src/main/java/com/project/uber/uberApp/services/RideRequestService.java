package com.project.uber.uberApp.services;

import com.project.uber.uberApp.entities.RideRequest;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
    void updateRideRequest(RideRequest rideRequest , RideRequestStatus rideRequestStatus);
}
