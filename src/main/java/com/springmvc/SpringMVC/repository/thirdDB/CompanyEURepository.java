package com.springmvc.SpringMVC.repository.thirdDB;

import com.springmvc.SpringMVC.model.thirdDB.CompanyEUModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEURepository extends JpaRepository<CompanyEUModel, Integer> {

    CompanyEUModel findCompanyModelByName(String name);
}
