package com.springmvc.SpringMVC.repository.thirdDB;

import com.springmvc.SpringMVC.model.thirdDB.CompanyEUModel;
import com.springmvc.SpringMVC.model.thirdDB.ProductEUModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEURepository extends PagingAndSortingRepository<ProductEUModel, Integer> {

    ProductEUModel findProductModelByName(String name);

    List<ProductEUModel> findAllByProductCompany(CompanyEUModel company, Pageable pageable);

    Page<ProductEUModel> findAllByPrice(Float price, Pageable pageable);

}
