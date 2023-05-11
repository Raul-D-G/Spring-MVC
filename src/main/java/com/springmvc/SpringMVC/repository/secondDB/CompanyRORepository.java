package com.springmvc.SpringMVC.repository.secondDB;

import com.springmvc.SpringMVC.model.secondDB.CompanyROModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRORepository extends JpaRepository<CompanyROModel, Integer> {

    CompanyROModel findCompanyModelByName(String name);
}
