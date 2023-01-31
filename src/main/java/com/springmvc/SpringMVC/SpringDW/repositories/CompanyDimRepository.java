package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.CompanyDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDimRepository extends JpaRepository<CompanyDim, Integer> {
}