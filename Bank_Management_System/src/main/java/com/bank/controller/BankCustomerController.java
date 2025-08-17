package com.bank.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.config.EmailConfig;
import com.bank.entity.CustomerDetails;
import com.bank.entity.Transaction;
import com.bank.service.BankCustomerService;
import com.bank.service.ResponseStructure;

@RestController
@RequestMapping("/bank")
public class BankCustomerController {

    @Autowired
    private BankCustomerService bankCustomerService;

    @Autowired
    private EmailConfig emailConfig;

    @PostMapping("/customerRegistration")
    public ResponseEntity<ResponseStructure<CustomerDetails>> customerRegistration(@RequestBody CustomerDetails customerDetails) {
        emailConfig.sendMailInTheFormatOfText(customerDetails.getEmailId(), "Registration Mail",
                "Hello " + customerDetails.getFirstName() + ", Thank You for registering with our application.");
        return bankCustomerService.customerRegistration(customerDetails);
    }

    @GetMapping("/pendingCustomers")
    public ResponseEntity<List<CustomerDetails>> getPendingCustomerDetails() {
        return bankCustomerService.getPendingCustomerDetails();
    }

    @GetMapping("/acceptedCustomers")
    public ResponseEntity<List<CustomerDetails>> getAcceptedCustomerDetails() {
        return bankCustomerService.getAcceptedCustomerDetails();
    }

    @GetMapping("/closingCustomers")
    public ResponseEntity<List<CustomerDetails>> getClosingCustomerDetails() {
        return bankCustomerService.getClosingCustomerDetails();
    }

    @PutMapping("/accept/{customerId}")
    public ResponseEntity<ResponseStructure<CustomerDetails>> acceptPendingDetails(@PathVariable int customerId) {
        return bankCustomerService.acceptPendingDetails(customerId);
    }

    @DeleteMapping("/reject/{customerId}")
    public ResponseEntity<ResponseStructure<CustomerDetails>> rejectRequest(@PathVariable int customerId) {
        return bankCustomerService.rejectRequest(customerId);
    }

    @GetMapping("/balance/{customerId}")
    public ResponseEntity<Double> viewBalance(@PathVariable int customerId) {
        return bankCustomerService.viewBalance(customerId);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam int senderId, @RequestParam int receiverId, @RequestParam BigDecimal amount) {
        return bankCustomerService.transferMoney(senderId, receiverId, amount.doubleValue());
    }

    @GetMapping("/transactions/{customerId}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable int customerId) {
        return bankCustomerService.getTransactionHistory(customerId);
    }
}







