package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.ClientModel;
import com.springmvc.SpringMVC.model.firstDB.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {

    ClientModel findClientModelByName(String name);

    List<ClientModel> findAllByCompany(CompanyModel company);
}
