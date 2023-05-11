package com.springmvc.SpringMVC.repository.secondDB;

import com.springmvc.SpringMVC.model.secondDB.BillingROId;
import com.springmvc.SpringMVC.model.secondDB.BillingROModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRORepository extends JpaRepository<BillingROModel, BillingROId> {

}