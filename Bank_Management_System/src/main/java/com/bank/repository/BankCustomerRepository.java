package com.bank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.entity.CustomerDetails;

public interface BankCustomerRepository extends JpaRepository<CustomerDetails, Integer> {
    List<CustomerDetails> readByStatus(String status);
    CustomerDetails findById(int customerId); // Returns CustomerDetails directly instead of Optional
}
