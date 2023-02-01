package com.springmvc.SpringMVC.SpringDW.repositories;

import com.springmvc.SpringMVC.SpringDW.models.BillingsFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillingsFactRepository extends JpaRepository<BillingsFact, Integer> {


    @Query(value = "SELECT time_dim.month, SUM(billings_fact.product_amount) as total_products " +
            "FROM billings_fact " +
            "JOIN time_dim ON billings_fact.time_id = time_dim.id " +
            "WHERE time_dim.year = :year " +
            "GROUP BY time_dim.month", nativeQuery = true)
    List<Object[]> findTotalBillingPerMonth(@Param("year") String year);

}