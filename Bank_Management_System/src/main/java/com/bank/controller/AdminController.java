package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bank.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;

	@GetMapping("/adminLogin/{emailId}/{password}")
	public ResponseEntity<String> adminLogin(@PathVariable("emailId") String emaild, @PathVariable("password") String password ) {
		return adminService.adminLogin(emaild, password);
	}
}
