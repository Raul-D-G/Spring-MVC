package com.springmvc.SpringMVC.repository;

import com.springmvc.SpringMVC.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Integer> {

    CompanyModel findCompanyModelByName(String name);
}
