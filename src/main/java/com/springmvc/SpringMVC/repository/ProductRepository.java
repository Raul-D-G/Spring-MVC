package com.springmvc.SpringMVC.repository;

import com.springmvc.SpringMVC.model.CompanyModel;
import com.springmvc.SpringMVC.model.InvoiceModel;
import com.springmvc.SpringMVC.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    ProductModel findProductModelByName(String name);

    List<ProductModel> findAllByProductCompany(CompanyModel company);

}
