package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.dto.DriverDTO;
import com.project.uber.uberApp.dto.RideDTO;
import com.project.uber.uberApp.dto.RideStartDTO;
import com.project.uber.uberApp.dto.RiderDTO;
import com.project.uber.uberApp.entities.Driver;
import com.project.uber.uberApp.entities.Ride;
import com.project.uber.uberApp.entities.RideRequest;
import com.project.uber.uberApp.entities.Rider;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.project.uber.uberApp.entities.enums.RideStatus;
import com.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.project.uber.uberApp.repositories.DriverRepository;
import com.project.uber.uberApp.repositories.RiderRepository;
import com.project.uber.uberApp.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final RiderRepository riderRepository;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("RideRequested can not be accepted, status is " + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();

        if (!currentDriver.getAvailable()) {
            throw new RuntimeException("Driver is not available");
        }

        //make driver unavailable
        Driver savedDriver = updateDriverAvailability(currentDriver , false);

        Ride createdRide = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(createdRide, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();

        if(!ride.getDriver().equals(currentDriver)) {
            throw new RuntimeException("Forbidden - This Ride is associated with another driver you can't cancel");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("The can not be Canceled invalid status " + ride.getRideStatus());
        }

        rideService.updateRideStatus(ride , RideStatus.CANCELLED) ;
        updateDriverAvailability(currentDriver , true);

        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideId, RideStartDTO rideStartDTO) {
        Ride ride = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();

        if (!currentDriver.equals(ride.getDriver())) {
            throw new RuntimeException("Forbidden - Different Driver Own this Ride");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride status is not CONFIRMED hence it can be started " + ride.getRideStatus());
        }

        if (!ride.getOtp().equals(rideStartDTO.getOtp())) {
            throw new RuntimeException("Ride OTP is not valid " + rideStartDTO.getOtp());
        }

        ride.setStartedAt(LocalDateTime.now());

        //update ride status as it is started now
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        //create payment object
        paymentService.createNewPayment(savedRide) ;

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public RideDTO endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();

        if (!currentDriver.equals(ride.getDriver())) {
            throw new RuntimeException("Forbidden - Different Driver Own this Ride");
        }

        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException("Ride status is not Ongoing hence it can be ended " + ride.getRideStatus());
        }

        ride.setEndedAt(LocalDateTime.now());

        Ride savedRide = rideService.updateRideStatus(ride , RideStatus.ENDED);
        updateDriverAvailability(currentDriver , true);

        //handle payment -> payment mode -> processed -> transaction created
        paymentService.processPayment(ride);

        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    //TODO - Make separate RiderDTO
    public RiderDTO rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId) ;
        Rider rider = ride.getRider();
        Double riderRating = rider.getRating() ;

        // If the rider doesn't have a rating yet, initialize it
        if (riderRating == null) {
            riderRating = 0.0;
        }

        Integer ratingCount = rider.getRatingCount() ;
        Double updatedRating = ((riderRating * ratingCount) + rating) / (ratingCount + 1);

        rider.setRating(updatedRating);
        rider.setRatingCount(ratingCount+1);

        //update rider and using here directly coz of cyclic dependency
        riderRepository.save(rider);
        return modelMapper.map(rider, RiderDTO.class);
    }

    @Override
    public DriverDTO getMyProfile() {
        Driver currentDriver = getCurrentDriver() ;
        return modelMapper.map(currentDriver , DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getMyAllRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver() ;
        return rideService.getAllRidesOfDriver(currentDriver , pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean availability) {
        driver.setAvailable(availability);
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver) ;
    }

}
