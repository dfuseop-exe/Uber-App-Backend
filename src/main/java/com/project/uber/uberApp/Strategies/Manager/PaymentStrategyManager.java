package com.project.uber.uberApp.Strategies.Manager;

import com.project.uber.uberApp.Strategies.Implementation.CashPaymentStrategy;
import com.project.uber.uberApp.Strategies.Implementation.WalletPaymentStrategy;
import com.project.uber.uberApp.Strategies.PaymentStrategy;
import com.project.uber.uberApp.entities.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
       return switch (paymentMethod) {
           case CASH -> cashPaymentStrategy ;
           case WALLET -> walletPaymentStrategy;
       };
    }
}
