package com.springmvc.SpringMVC.repository;

import com.springmvc.SpringMVC.model.BillingId;
import com.springmvc.SpringMVC.model.BillingModel;
import com.springmvc.SpringMVC.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<BillingModel, BillingId> {
    BillingModel findByInvoiceIdAndProductId(Integer invoiceId, Integer productId);

    BillingModel findBillingModelByInvoice(InvoiceModel invoiceModel);

    List<BillingModel> findAllByInvoice(InvoiceModel invoiceModel);

}