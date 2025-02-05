package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.Strategies.Manager.PaymentStrategyManager;
import com.project.uber.uberApp.entities.Payment;
import com.project.uber.uberApp.entities.Ride;
import com.project.uber.uberApp.entities.enums.PaymentStatus;
import com.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.project.uber.uberApp.repositories.PaymentRepository;
import com.project.uber.uberApp.repositories.RiderRepository;
import com.project.uber.uberApp.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    private final RiderRepository riderRepository;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                        .orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride : "+ ride.getId()));
        //this method will internally decide
        paymentStrategyManager.getPaymentStrategy(ride.getPaymentMethod()).processPayment(payment); ;
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment
                .builder()
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .ride(ride)
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Payment payment , PaymentStatus paymentStatus) {

    }
}
