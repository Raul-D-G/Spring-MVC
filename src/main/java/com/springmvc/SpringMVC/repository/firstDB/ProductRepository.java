package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.CompanyModel;
import com.springmvc.SpringMVC.model.firstDB.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Integer> {

    ProductModel findProductModelByName(String name);

    List<ProductModel> findAllByProductCompany(CompanyModel company, Pageable pageable);

    Page<ProductModel> findAllByPrice(Float price, Pageable pageable);

}
