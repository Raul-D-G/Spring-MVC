package com.springmvc.SpringMVC.repository;

import com.springmvc.SpringMVC.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    ProductModel findProductModelByName(String name);

}
