package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bank.dao.BankCustomerDao;
import com.bank.dao.TransactionDao;
import com.bank.entity.CustomerDetails;
import com.bank.entity.Transaction;
import com.bank.exception.BankCustomerDetailsException;

@Service
public class BankCustomerService {

    @Autowired
    private BankCustomerDao bankCustomerDao;

    @Autowired
    private TransactionDao transactionDao;

    public ResponseEntity<ResponseStructure<CustomerDetails>> customerRegistration(CustomerDetails customerDetails) {
        List<CustomerDetails> allCustomers = bankCustomerDao.getAllCustomerDetails();

        boolean emailExists = allCustomers.stream()
                .anyMatch(existing -> existing.getEmailId().equals(customerDetails.getEmailId()));
        if (emailExists) {
            throw new BankCustomerDetailsException("Email already exists.");
        }

        boolean aadharExists = allCustomers.stream()
                .anyMatch(existing -> existing.getAadharNumber() == customerDetails.getAadharNumber());
        if (aadharExists) {
            throw new BankCustomerDetailsException("Aadhar number already exists.");
        }

        CustomerDetails savedCustomer = bankCustomerDao.insertCustomerDetails(customerDetails);
        ResponseStructure<CustomerDetails> response = new ResponseStructure<>(
                "Registration successful.", savedCustomer, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<List<CustomerDetails>> getPendingCustomerDetails() {
        List<CustomerDetails> pending = bankCustomerDao.getPendingCustomerDetails();
        if (pending.isEmpty()) throw new BankCustomerDetailsException("No Pending Customer Data Found.");
        return new ResponseEntity<>(pending, HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerDetails>> getAcceptedCustomerDetails() {
        List<CustomerDetails> accepted = bankCustomerDao.getAcceptedCustomerDetails();
        if (accepted.isEmpty()) throw new BankCustomerDetailsException("No Accepted Customer Data Found.");
        return new ResponseEntity<>(accepted, HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerDetails>> getClosingCustomerDetails() {
        List<CustomerDetails> closing = bankCustomerDao.getClosingCustomerDetails();
        if (closing.isEmpty()) throw new BankCustomerDetailsException("No Closing Customer Data Found.");
        return new ResponseEntity<>(closing, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<CustomerDetails>> acceptPendingDetails(int customerId) {
        Random random = new Random();
        int accountNum = 1000000 + random.nextInt(9000000);
        int pin = 1000 + random.nextInt(9000);

        CustomerDetails updatedCustomer = bankCustomerDao.updateAccountNumberAndPinNumberById(accountNum, pin, customerId);
        ResponseStructure<CustomerDetails> response = new ResponseStructure<>(
                "Account Number and PIN Generated Successfully.", updatedCustomer, HttpStatus.ACCEPTED.value());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<ResponseStructure<CustomerDetails>> rejectRequest(int customerId) {
        CustomerDetails rejectedCustomer = bankCustomerDao.rejectRequest(customerId);
        ResponseStructure<CustomerDetails> response = new ResponseStructure<>(
                "Request rejected.", rejectedCustomer, HttpStatus.GONE.value());

        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    public ResponseEntity<Double> viewBalance(int customerId) {
        CustomerDetails customer = bankCustomerDao.findById(customerId);
        if (customer == null) throw new BankCustomerDetailsException("Customer not found.");
        return new ResponseEntity<>(customer.getAmount(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> transferMoney(int senderId, int receiverId, double amount) {
        CustomerDetails sender = bankCustomerDao.findById(senderId);
        CustomerDetails receiver = bankCustomerDao.findById(receiverId);

        if (sender == null || receiver == null) throw new BankCustomerDetailsException("Sender or Receiver not found.");
        if (sender.getAmount() < amount) throw new BankCustomerDetailsException("Insufficient balance.");

        sender.setAmount(sender.getAmount() - amount);
        receiver.setAmount(receiver.getAmount() + amount);

        bankCustomerDao.insertCustomerDetails(sender);
        bankCustomerDao.insertCustomerDetails(receiver);

        Transaction transaction = new Transaction(senderId, receiverId, amount, LocalDateTime.now());
        transactionDao.saveTransaction(transaction);

        return new ResponseEntity<>("Transfer successful.", HttpStatus.OK);
    }

    public ResponseEntity<List<Transaction>> getTransactionHistory(int customerId) {
        List<Transaction> transactions = transactionDao.findBySenderIdOrReceiverId(customerId, customerId);
        if (transactions.isEmpty()) throw new BankCustomerDetailsException("No transaction history found.");
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
