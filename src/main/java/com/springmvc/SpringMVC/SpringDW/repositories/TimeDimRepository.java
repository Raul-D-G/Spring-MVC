package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.TimeDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDimRepository extends JpaRepository<TimeDim, Integer> {
}