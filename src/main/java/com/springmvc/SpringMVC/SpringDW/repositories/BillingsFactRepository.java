package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.BillingsFact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingsFactRepository extends JpaRepository<BillingsFact, Integer> {
}