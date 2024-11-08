package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.Strategies.Manager.DriverMatchingStrategyManager;
import com.project.uber.uberApp.Strategies.Manager.FareStrategyManager;
import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.RideDTO;
import com.project.uber.uberApp.dto.RideRequestDTO;
import com.project.uber.uberApp.dto.RiderDTO;
import com.project.uber.uberApp.entities.Driver;
import com.project.uber.uberApp.entities.RideRequest;
import com.project.uber.uberApp.entities.Rider;
import com.project.uber.uberApp.entities.User;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.project.uber.uberApp.repositories.RideRequestRepository;
import com.project.uber.uberApp.repositories.RiderRepository;
import com.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper ;
    private final DriverMatchingStrategyManager driverMatchingStrategyManager ;
    private final FareStrategyManager fareStrategyManager ;
    private final RideRequestRepository rideRequestRepository ;
    private final RiderRepository riderRepository;
    private final DistanceServiceOSRMImpl distanceServiceOSRMImpl;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider() ;
        RideRequest rideRequest = modelMapper.map(rideRequestDTO , RideRequest.class) ;
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        // remove - TODO test only
        rideRequest.setDistance(distanceServiceOSRMImpl.calculateDistance(rideRequest.getPickUpLocation() , rideRequest.getDropOffLocation()));

        Double fare = fareStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest) ;
        List<Driver> drivers = driverMatchingStrategyManager.getDriverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest) ;

        //TODO - Send Notification to all drivers

        return modelMapper.map(savedRideRequest, RideRequestDTO.class) ;
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getMyAllRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build() ;

        return riderRepository.save(rider) ;
    }

    @Override
    public Rider getCurrentRider() {
//        TODO :- Implement Spring Security
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found") ) ;
    }
}
