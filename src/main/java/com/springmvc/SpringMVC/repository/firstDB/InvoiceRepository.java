package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.CompanyModel;
import com.springmvc.SpringMVC.model.firstDB.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer> {

    InvoiceModel findInvoiceModelByNumber(Integer number);

    List<InvoiceModel> findAllByInvoiceCompany(CompanyModel company);
}
