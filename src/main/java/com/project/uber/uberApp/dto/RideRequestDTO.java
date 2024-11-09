package com.project.uber.uberApp.dto;

import com.project.uber.uberApp.entities.enums.PaymentMethod;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
    private Long id;
    private PointDTO pickUpLocation;
    private PointDTO dropOffLocation;
    private LocalDateTime requestedTime;
    private RiderDTO rider;
    private Double fare;
    private Double distance;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}
