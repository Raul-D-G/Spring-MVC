package com.springmvc.SpringMVC.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.SpringMVC.model.firstDB.CompanyDim;
import com.springmvc.SpringMVC.model.firstDB.ProductDim;
import com.springmvc.SpringMVC.model.firstDB.UserModel;
import com.springmvc.SpringMVC.repository.firstDB.CompanyDimRepository;
import com.springmvc.SpringMVC.repository.firstDB.ProductDimRepository;
import com.springmvc.SpringMVC.repository.firstDB.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductDimController {


    @Autowired
    ProductDimRepository productDimRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyDimRepository companyDimRepository;


    @GetMapping("/productsgb")
    public String products(@RequestParam(value = "userName") String userName, @RequestParam(value = "page", required = false) Optional<Integer> page, @RequestParam(value = "sortBy", required = false) Optional<String> sortBy, final Model model) {

        sortBy.ifPresent(s -> model.addAttribute("sortBy", s));

        if (sortBy.isEmpty()) {
            model.addAttribute("sortBy", "price");
        }
        sortBy.ifPresent(System.out::println);

        UserModel user = userRepository.findUserModelByUserName(userName);


        CompanyDim company = companyDimRepository.findCompanyDimById(user.getCompany().getId());

        List<ProductDim> products = Collections.emptyList();
        if (company != null) {
            products = productDimRepository.findAllByProductCompany(company, PageRequest.of(page.orElse(0), 4,
                    Sort.Direction.ASC, sortBy.orElse("price")));


            if (products.size() == 4) {
                if (page.isPresent()) {
                    model.addAttribute("nextPage", page.get() + 1);
                } else {
                    model.addAttribute("nextPage", 1);
                }
            }

//            if (page.isEmpty()) {
//                model.addAttribute("nextPage", 0);
//            }

        }

        model.addAttribute("products", products);
        model.addAttribute("user", user);

        return "productGBList";
    }


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
