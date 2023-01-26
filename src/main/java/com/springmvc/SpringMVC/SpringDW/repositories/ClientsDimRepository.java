package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.ClientsDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsDimRepository extends JpaRepository<ClientsDim, Integer> {
    ClientsDim findClientsDimByName(String name);
}
