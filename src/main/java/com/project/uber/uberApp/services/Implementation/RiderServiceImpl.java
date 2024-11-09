package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.Strategies.Manager.DriverMatchingStrategyManager;
import com.project.uber.uberApp.Strategies.Manager.FareStrategyManager;
import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.RideDTO;
import com.project.uber.uberApp.dto.RideRequestDTO;
import com.project.uber.uberApp.dto.RiderDTO;
import com.project.uber.uberApp.entities.*;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.project.uber.uberApp.entities.enums.RideStatus;
import com.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.project.uber.uberApp.repositories.RideRequestRepository;
import com.project.uber.uberApp.repositories.RiderRepository;
import com.project.uber.uberApp.services.DriverService;
import com.project.uber.uberApp.services.RideService;
import com.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final DriverMatchingStrategyManager driverMatchingStrategyManager;
    private final FareStrategyManager fareStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final DistanceServiceOSRMImpl distanceServiceOSRMImpl;
    private final RideService rideService;
    private final DriverService driverService;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        // remove - TODO test only
        rideRequest.setDistance(distanceServiceOSRMImpl.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation()));

        Double fare = fareStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = driverMatchingStrategyManager.getDriverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

        //TODO - Send Notification to all drivers

        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    @Transactional
    public RideDTO cancelRide(Long rideId) {
        Rider rider = getCurrentRider() ;
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("FORBIDDEN :- Rider trying to cancel a ride which is not associated with this ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("You can not cancel a ride which is not confirmed yet , or already cancelled");
        }

        Ride updatedRide = rideService.updateRideStatus(ride , RideStatus.CANCELLED) ;

        Driver driver = ride.getDriver() ;
        driverService.updateDriverAvailability(driver , true);

        return modelMapper.map(updatedRide, RideDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId) ;
        Driver driver = ride.getDriver();
        Double driverRating = driver.getRating() ;

        // If the rider doesn't have a rating yet, initialize it
        if (driverRating == null) {
            driverRating = 0.0;
        }

        Integer ratingCount = driver.getRatingCount() ;
        Double updatedRating = (driverRating * ratingCount + rating) / (ratingCount + 1);

        driver.setRating(updatedRating);
        driver.setRatingCount(ratingCount+1);

        driverService.updateDriver(driver) ;
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    public RiderDTO getMyProfile() {
        Rider rider = getCurrentRider();
        return modelMapper.map(rider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getMyAllRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider() ;
        return rideService.getAllRidesOfRider(currentRider.getId() , pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();

        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
//        TODO :- Implement Spring Security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found"));
    }

    @Override
    public Rider updateRider(Rider rider) {
        return riderRepository.save(rider);
    }
}
