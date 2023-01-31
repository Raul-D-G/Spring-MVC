package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.ClientDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDimRepository extends JpaRepository<ClientDim, Integer> {
    ClientDim findClientsDimByName(String name);
}
