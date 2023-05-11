package com.springmvc.SpringMVC.repository.thirdDB;

import com.springmvc.SpringMVC.model.thirdDB.BillingEUId;
import com.springmvc.SpringMVC.model.thirdDB.BillingEUModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingEURepository extends JpaRepository<BillingEUModel, BillingEUId> {

}