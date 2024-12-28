package com.project.uber.uberApp.services;

import com.project.uber.uberApp.entities.Ride;
import com.project.uber.uberApp.entities.User;
import com.project.uber.uberApp.entities.Wallet;
import com.project.uber.uberApp.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, double amount , String transactionId , Ride ride , TransactionMethod transactionMethod);
    void withdrawMoneyFromWallet(User user, double amount);
    Wallet findWalletById(Long id);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
    Wallet deductMoneyFromWallet(User user, double amount , String transactionId , Ride ride , TransactionMethod transactionMethod);
}
