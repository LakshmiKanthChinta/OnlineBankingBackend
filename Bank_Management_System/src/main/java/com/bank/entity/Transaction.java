package com.bank.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {
    public Transaction(int senderId, int receiverId, double amount, LocalDateTime timestamp) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    
    @Column(nullable = false)
    private int senderId;
    
    @Column(nullable = false)
    private int receiverId;
    
    @Column(nullable = false)
    private double amount;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    
}
