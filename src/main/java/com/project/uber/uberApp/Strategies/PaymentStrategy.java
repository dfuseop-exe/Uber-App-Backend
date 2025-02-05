package com.project.uber.uberApp.Strategies;

import com.project.uber.uberApp.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3 ;
    void processPayment(Payment payment);
}
