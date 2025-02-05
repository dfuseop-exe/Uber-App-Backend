package com.project.uber.uberApp.Strategies.Implementation;

import com.project.uber.uberApp.Strategies.PaymentStrategy;
import com.project.uber.uberApp.entities.Driver;
import com.project.uber.uberApp.entities.Payment;
import com.project.uber.uberApp.entities.Rider;
import com.project.uber.uberApp.entities.enums.PaymentStatus;
import com.project.uber.uberApp.entities.enums.TransactionMethod;
import com.project.uber.uberApp.repositories.PaymentRepository;
import com.project.uber.uberApp.services.PaymentService;
import com.project.uber.uberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService ;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Rider rider = payment.getRide().getRider() ;
        Driver driver = payment.getRide().getDriver() ;

        double driversEarning = payment.getAmount() - (payment.getAmount() * PLATFORM_COMMISSION) ;

        walletService.deductMoneyFromWallet(rider.getUser() , payment.getAmount() , null , payment.getRide() , TransactionMethod.RIDE) ;
        walletService.addMoneyToWallet(driver.getUser() , driversEarning , null , payment.getRide() , TransactionMethod.RIDE) ;

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
