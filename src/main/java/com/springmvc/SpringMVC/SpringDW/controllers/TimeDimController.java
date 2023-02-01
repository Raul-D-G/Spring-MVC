package com.springmvc.SpringMVC.SpringDW.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.SpringMVC.SpringDW.repositories.TimeDimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TimeDimController {

    @Autowired
    TimeDimRepository timeDimRepository;


    @GetMapping("/clientStatistic-admin")
    public String getClientStatistic(Model model) throws JsonProcessingException {
        List<Object[]> data = timeDimRepository.findBillingsPriceCompany();

        ObjectMapper objectMapper = new ObjectMapper();
        String dataJson = objectMapper.writeValueAsString(data);
        model.addAttribute("data", dataJson);

        return "billing-chart4";
    }
}
