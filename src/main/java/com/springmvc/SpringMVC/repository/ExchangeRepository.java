package com.springmvc.SpringMVC.repository;

import com.springmvc.SpringMVC.model.ExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeModel, Integer> {

    ExchangeModel findExchangeModelByCurrency(String name);
}
