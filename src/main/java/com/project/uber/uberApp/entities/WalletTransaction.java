package com.project.uber.uberApp.entities;

import com.project.uber.uberApp.entities.enums.TransactionMethod;
import com.project.uber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Double amount ;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType ;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod ;

    @OneToOne
    private Ride ride ;

    private String transactionId ;

    @CreationTimestamp
    private LocalDateTime timestamp ;

    @ManyToOne
    private Wallet wallet ;
}