package com.springmvc.SpringMVC.SpringDW.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.SpringMVC.SpringDW.repositories.BillingsFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BillingsFactController {

    @Autowired
    private BillingsFactRepository billingsFactRepository;

    @GetMapping("/products-month")
    public String getBillingData(Model model) throws JsonProcessingException {
        List<Object[]> data = billingsFactRepository.findTotalBillingPerMonth("2023");

        ObjectMapper objectMapper = new ObjectMapper();
        String dataJson = objectMapper.writeValueAsString(data);
        model.addAttribute("data", dataJson);

        return "billing-chart1";
    }

    @GetMapping("/mostInvoicedProducts-year")
    public String getMostInvoicedProducts(Model model) throws JsonProcessingException {
        List<Object[]> data = billingsFactRepository.findMostInvoicedProductsYear("2023");

        ObjectMapper objectMapper = new ObjectMapper();
        String dataJson = objectMapper.writeValueAsString(data);
        model.addAttribute("data", dataJson);

        return "billing-chart2";
    }
}
