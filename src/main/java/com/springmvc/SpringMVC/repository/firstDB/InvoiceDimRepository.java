package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.InvoiceDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDimRepository extends JpaRepository<InvoiceDim, Integer> {
}