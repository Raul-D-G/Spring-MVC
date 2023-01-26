package com.springmvc.SpringMVC;

import java.util.List;

import com.springmvc.SpringMVC.SpringDW.models.ClientsDim;
import com.springmvc.SpringMVC.SpringDW.repositories.ClientsDimRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.SpringMVC.model.ClientModel;
import com.springmvc.SpringMVC.repository.ClientRepository;

@RestController
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired

    ClientsDimRepository clientsDimRepository;


    @RequestMapping(value = "/primary", method = RequestMethod.GET)
    public List<ClientModel> getPrimaryDatabaseData() {
        return clientRepository.findAll();
    }


    @RequestMapping(value = "/secondary", method = RequestMethod.GET)
    public List<ClientsDim> getSecondaryDatabaseData() {
        return clientsDimRepository.findAll();
    }
}