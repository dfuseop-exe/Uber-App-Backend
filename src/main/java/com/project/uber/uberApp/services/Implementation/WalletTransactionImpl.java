package com.project.uber.uberApp.services.Implementation;

import com.project.uber.uberApp.dto.WalletTransactionDTO;
import com.project.uber.uberApp.repositories.WalletTransactionRepository;
import com.project.uber.uberApp.entities.WalletTransaction;
import com.project.uber.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public WalletTransaction createNewWalletTransaction(WalletTransaction walletTransaction) {
        return walletTransactionRepository.save(walletTransaction) ;
    }

}
