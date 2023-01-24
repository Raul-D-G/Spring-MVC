package com.springmvc.SpringMVC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.SpringMVC.model.ClientModel;
import com.springmvc.SpringMVC.repository.ClientRepository;
import com.springmvc.SpringDW.Manager;
import com.springmvc.SpringDW.ManagerRepository;

@RestController
public class TestController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ManagerRepository managerRepository;


    @RequestMapping(value = "/primary", method = RequestMethod.GET)
    public List<ClientModel> getPrimaryDatabaseData() {
        return clientRepository.findAll();
    }

    @RequestMapping(value = "/secondary", method = RequestMethod.GET)
    public List<Manager> getSecondaryDatabaseData() {
        return managerRepository.findAll();
    }
}