package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.TimeDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeDimRepository extends JpaRepository<TimeDim, Integer> {


    @Query(value = "SELECT company_id, quarter, " +
            "SUM(billing_price_with_tva) valoare, " +
            "SUM(SUM(billing_price_with_tva)) " +
            "OVER (PARTITION BY company_id " +
            "ORDER BY     company_id, quarter " +
            "ROWS UNBOUNDED PRECEDING) valoare_cumulata " +
            "FROM   billings_fact b, time_dim t " +
            "WHERE  b.time_id=t.id " +
            "AND    year=2023 " +
            "AND    company_id  = 2 " +
            "GROUP BY company_id, quarter", nativeQuery = true)
    List<Object[]> findBillingsPriceCompany();
}