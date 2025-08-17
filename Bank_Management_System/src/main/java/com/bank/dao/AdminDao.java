package com.bank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.entity.AdminDetails;
import com.bank.repository.AdminRepository;

@Repository
public class AdminDao {
	@Autowired
	AdminRepository adminRepository;
	
	public AdminDetails adminLogin(String emaiId,String password) {
		return adminRepository.getByEmailIdAndPassword(emaiId, password);
	}
}
