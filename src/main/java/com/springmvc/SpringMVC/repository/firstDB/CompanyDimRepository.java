package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.CompanyDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDimRepository extends JpaRepository<CompanyDim, Integer> {

    CompanyDim findCompanyDimById(Integer id);
}