package com.subspaceclone.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
