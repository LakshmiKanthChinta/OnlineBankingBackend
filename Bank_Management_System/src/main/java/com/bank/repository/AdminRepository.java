package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.AdminDetails;

public interface AdminRepository extends JpaRepository<AdminDetails, Integer>{

	AdminDetails getByEmailIdAndPassword(String emaiId,String password);
}
