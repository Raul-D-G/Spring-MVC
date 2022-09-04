package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.config.AuthenticationSuccessHandlerImpl;
import com.springmvc.SpringMVC.model.ProductModel;
import com.springmvc.SpringMVC.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/products")
    public String home(final Model model) {

        List<ProductModel> products = productRepository.findAll();

        logger.info("The following products were found: " + products);

        model.addAttribute("products", productRepository.findAll());
        
        return "productList";
    }
}