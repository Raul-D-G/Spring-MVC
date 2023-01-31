package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.InvoiceDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDimRepository extends JpaRepository<InvoiceDim, Integer> {
}