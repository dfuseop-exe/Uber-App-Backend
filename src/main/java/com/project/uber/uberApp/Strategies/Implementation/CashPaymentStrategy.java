package com.project.uber.uberApp.Strategies.Implementation;

import com.project.uber.uberApp.Strategies.PaymentStrategy;
import com.project.uber.uberApp.entities.Driver;
import com.project.uber.uberApp.entities.Payment;
import com.project.uber.uberApp.entities.enums.PaymentStatus;
import com.project.uber.uberApp.entities.enums.TransactionMethod;
import com.project.uber.uberApp.repositories.PaymentRepository;
import com.project.uber.uberApp.services.PaymentService;
import com.project.uber.uberApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Rider ->  rent 100 cash
// Driver -> 30 deduct from drivers wallet

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platform_commission = payment.getAmount() * PLATFORM_COMMISSION ;

        //this method will handle deduction of money from the wallet and then have a method to create transaction
        walletService.deductMoneyFromWallet(driver.getUser() , platform_commission , null , payment.getRide() , TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
