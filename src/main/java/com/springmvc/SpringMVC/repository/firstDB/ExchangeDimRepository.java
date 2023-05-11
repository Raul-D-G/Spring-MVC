package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.ExchangeDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeDimRepository extends JpaRepository<ExchangeDim, Integer> {
}