package com.springmvc.SpringMVC;

import java.util.List;

import com.springmvc.SpringMVC.model.firstDB.ClientDim;
import com.springmvc.SpringMVC.repository.firstDB.ClientDimRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.SpringMVC.model.firstDB.ClientModel;
import com.springmvc.SpringMVC.repository.firstDB.ClientRepository;

@RestController
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientDimRepository clientDimRepository;


    @RequestMapping(value = "/primary", method = RequestMethod.GET)
    public List<ClientModel> getPrimaryDatabaseData() {
        return clientRepository.findAll();
    }


    @RequestMapping(value = "/secondary", method = RequestMethod.GET)
    public List<ClientDim> getSecondaryDatabaseData() {
        return clientDimRepository.findAll();
    }
}