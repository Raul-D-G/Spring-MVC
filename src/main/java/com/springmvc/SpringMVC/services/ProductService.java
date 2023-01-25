package com.springmvc.SpringMVC.services;

import com.springmvc.SpringMVC.model.ProductModel;
import com.springmvc.SpringMVC.repository.ProductRepository;
import com.springmvc.SpringMVC.services.interfaces.iProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements iProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void createProduct(ProductModel product) {
        productRepository.save(product);
    }

}
