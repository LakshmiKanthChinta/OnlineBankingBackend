package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.dao.AdminDao;
import com.bank.entity.AdminDetails;
import com.bank.exception.AdminDetailsException;

@Service
public class AdminService {

	@Autowired
	AdminDao adminDao;
	
	public ResponseEntity<String> adminLogin(String emaiId,String password) {
		
		 AdminDetails adminDetails = adminDao.adminLogin(emaiId, password);
		 if(adminDetails != null) {
			 return new ResponseEntity<String>("Admin login successful",HttpStatus.OK);
		 }else {
			 throw new AdminDetailsException("Invalid Email or Password");
		 }
	}
}
