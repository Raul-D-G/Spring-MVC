package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.ClientDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDimRepository extends JpaRepository<ClientDim, Integer> {
    ClientDim findClientsDimByName(String name);
}
