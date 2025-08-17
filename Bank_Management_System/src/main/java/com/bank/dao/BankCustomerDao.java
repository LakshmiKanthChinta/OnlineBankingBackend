package com.bank.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bank.entity.CustomerDetails;
import com.bank.exception.BankCustomerDetailsException;
import com.bank.repository.BankCustomerRepository;

@Repository
public class BankCustomerDao {

    @Autowired
    private BankCustomerRepository bankCustomerRepository;

    public CustomerDetails insertCustomerDetails(CustomerDetails customerDetails) {
        return bankCustomerRepository.save(customerDetails);
    }

    public List<CustomerDetails> getAllCustomerDetails() {
        return bankCustomerRepository.findAll();
    }

    public List<CustomerDetails> getPendingCustomerDetails() {
        return bankCustomerRepository.readByStatus("Pending");
    }

    public List<CustomerDetails> getAcceptedCustomerDetails() {
        return bankCustomerRepository.readByStatus("Accepted");
    }

    public List<CustomerDetails> getClosingCustomerDetails() {
        return bankCustomerRepository.readByStatus("Closing");
    }

    public CustomerDetails updateAccountNumberAndPinNumberById(int accountNum, int pin, int customerId) {
        CustomerDetails customer = bankCustomerRepository.findById(customerId);
        if (customer == null) {
            throw new BankCustomerDetailsException("No data found.");
        }
        customer.setAccountNumber(accountNum);
        customer.setPin(pin);
        customer.setStatus("Accepted");
        return bankCustomerRepository.save(customer);
    }

    public CustomerDetails rejectRequest(int customerId) {
        CustomerDetails customer = bankCustomerRepository.findById(customerId);
        if (customer == null) {
            throw new BankCustomerDetailsException("No data found.");
        }
        bankCustomerRepository.delete(customer);
        return customer;
    }

    public CustomerDetails findById(int customerId) {
        CustomerDetails customer = bankCustomerRepository.findById(customerId);
        if (customer == null) {
            throw new BankCustomerDetailsException("Customer not found.");
        }
        return customer;
    }

    public void save(CustomerDetails customerDetails) {
        bankCustomerRepository.save(customerDetails);
    }
}
