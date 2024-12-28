package com.project.uber.uberApp.services;

import com.project.uber.uberApp.dto.WalletTransactionDTO;
import com.project.uber.uberApp.entities.WalletTransaction;

public interface WalletTransactionService {
    WalletTransaction createNewWalletTransaction(WalletTransaction  walletTransaction );
}
