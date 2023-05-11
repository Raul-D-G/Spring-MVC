package com.springmvc.SpringMVC.repository.secondDB;

import com.springmvc.SpringMVC.model.secondDB.CompanyROModel;
import com.springmvc.SpringMVC.model.secondDB.ProductROModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRORepository extends PagingAndSortingRepository<ProductROModel, Integer> {

    ProductROModel findProductModelByName(String name);

    List<ProductROModel> findAllByProductCompany(CompanyROModel company, Pageable pageable);

    Page<ProductROModel> findAllByPrice(Float price, Pageable pageable);

}
