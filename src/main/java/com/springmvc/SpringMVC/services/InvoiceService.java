package com.springmvc.SpringMVC.services;

import com.springmvc.SpringMVC.model.BillingId;
import com.springmvc.SpringMVC.model.BillingModel;
import com.springmvc.SpringMVC.model.InvoiceModel;
import com.springmvc.SpringMVC.model.ProductModel;
import com.springmvc.SpringMVC.repository.BillingRepository;
import com.springmvc.SpringMVC.repository.InvoiceRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    BillingRepository billingRepository;

    public void addProduct(@NotNull InvoiceModel invoice, ProductModel newProduct, String unit, int amount) {


        BillingModel billing = invoice.getBillings()
                .stream()
                .filter(b -> b.getProduct().equals(newProduct))
                .findFirst()
                .orElse(null);
        if (billing != null) {
            billing.setAmount(billing.getAmount() + 1);
        } else {
            billing = new BillingModel();
            BillingId id = new BillingId(invoice.getId(), newProduct.getId());
            billing.setId(id);
            billing.setInvoice(invoice);
            billing.setProduct(newProduct);
            billing.setUnit(unit);
            billing.setAmount(amount != 0 ? amount : 1);
            invoice.getBillings().add(billing);
            newProduct.getBillings().add(billing);
        }
        billingRepository.save(billing);
    }
}
