package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.ProductDim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDimRepository extends JpaRepository<ProductDim, Integer> {
}