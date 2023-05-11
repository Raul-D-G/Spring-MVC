package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.BillingsFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingsFactRepository extends JpaRepository<BillingsFact, Integer> {


    @Query(value = "SELECT time_dim.month, SUM(billings_fact.product_amount) as total_products " +
            " FROM billings_fact " +
            " JOIN time_dim ON billings_fact.time_id = time_dim.id " +
            " WHERE time_dim.year = :year " +
            " GROUP BY time_dim.month", nativeQuery = true)
    List<Object[]> findTotalBillingPerMonth(@Param("year") String year);


    @Query(value = "SELECT products.name, SUM(billings_fact.product_amount) AS total_units_sold " +
            "FROM billings_fact " +
            "JOIN products ON billings_fact.product_id = products.id " +
            "JOIN time_dim ON billings_fact.time_id = time_dim.id " +
            "WHERE time_dim.year = :year " +
            "GROUP BY products.name ORDER BY total_units_sold DESC", nativeQuery = true)
    List<Object[]> findMostInvoicedProductsYear(@Param("year") String year);

}