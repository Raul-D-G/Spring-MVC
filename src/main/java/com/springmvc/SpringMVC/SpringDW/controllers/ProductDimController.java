package com.springmvc.SpringMVC.SpringDW.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.SpringMVC.SpringDW.repositories.ProductDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductDimController {


    @Autowired
    ProductDimRepository productDimRepository;


    @GetMapping("/mostExpensiveProducts-ue")
    public String getMostInvoicedProductsEu(Model model) throws JsonProcessingException {
        List<Object[]> data = productDimRepository.mostExpensiveProductsUe();

        ObjectMapper objectMapper = new ObjectMapper();
        String dataJson = objectMapper.writeValueAsString(data);
        model.addAttribute("data", dataJson);

        return "billing-chart3";
    }

    @GetMapping("/mostExpensiveProducts-ro")
    public String getMostInvoicedProductsRo(Model model) throws JsonProcessingException {
        List<Object[]> data = productDimRepository.mostExpensiveProductsRo();

        ObjectMapper objectMapper = new ObjectMapper();
        String dataJson = objectMapper.writeValueAsString(data);
        model.addAttribute("data", dataJson);

        return "billing-chart3";
    }
}
