package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.entities.RideRequest;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.project.uber.uberApp.repositories.RideRequestRepository;
import com.project.uber.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(() ->
                new ResourceNotFoundException("RideRequest with id " + rideRequestId + " not found"));
    }

    @Override
    public void updateRideRequest(RideRequest rideRequest, RideRequestStatus rideRequestStatus) {
        RideRequest tobeSavedRideRequest = findRideRequestById(rideRequest.getId());
        rideRequest.setRideRequestStatus(rideRequestStatus);
        rideRequestRepository.save(tobeSavedRideRequest);
    }
}
