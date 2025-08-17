package com.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	private String lastName;
	private String address;
	
	@Column(name = "Email_Id", unique = true, nullable = false)
	private String emailId;
	
	private String gender;
	
	@Column(nullable = false, unique = true)
	private long aadharNumber;
	
	private double amount;
	
	private long accountNumber;
	private int pin;
	private String status;
}
