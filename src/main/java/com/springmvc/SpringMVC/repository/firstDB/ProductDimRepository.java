package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.ProductDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDimRepository extends JpaRepository<ProductDim, Integer> {


    @Query(value = "SELECT category, name, price, FIRST_VALUE(name) " +
            "OVER (ORDER BY price DESC " +
            "ROWS BETWEEN UNBOUNDED PRECEDING " +
            "AND UNBOUNDED FOLLOWING) " +
            "AS den_prod_cu_pret_maxim " +
            "FROM  (SELECT * " +
            "FROM products_dim_part PARTITION (P_MADE_IN_ROMANIA) " +
            "ORDER BY id) ", nativeQuery = true)
    List<Object[]> mostExpensiveProductsRo();

    @Query(value = "SELECT category, name, price, FIRST_VALUE(name) " +
            "OVER (ORDER BY price DESC " +
            "ROWS BETWEEN UNBOUNDED PRECEDING " +
            "AND UNBOUNDED FOLLOWING) " +
            "AS den_prod_cu_pret_maxim " +
            "FROM  (SELECT * " +
            "FROM products_dim_part PARTITION (P_MADE_IN_UE) " +
            "ORDER BY id) ", nativeQuery = true)
    List<Object[]> mostExpensiveProductsUe();
}