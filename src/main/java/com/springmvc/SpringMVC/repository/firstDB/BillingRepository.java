package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.BillingId;
import com.springmvc.SpringMVC.model.firstDB.BillingModel;
import com.springmvc.SpringMVC.model.firstDB.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<BillingModel, BillingId> {
    BillingModel findByInvoiceIdAndProductId(Integer invoiceId, Integer productId);

    BillingModel findBillingModelByInvoice(InvoiceModel invoiceModel);

    List<BillingModel> findAllByInvoice(InvoiceModel invoiceModel);

}