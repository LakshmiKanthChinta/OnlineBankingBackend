package com.bank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.entity.Transaction;
import com.bank.repository.TransactionRepository;

@Repository
public class TransactionDao {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findBySenderIdOrReceiverId(int senderId, int receiverId) {
        return transactionRepository.findBySenderIdOrReceiverId(senderId, receiverId);
    }
}
