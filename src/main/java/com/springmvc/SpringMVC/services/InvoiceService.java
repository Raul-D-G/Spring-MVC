package com.springmvc.SpringMVC.services;

import com.springmvc.SpringMVC.model.InvoiceModel;
import com.springmvc.SpringMVC.model.ProductModel;
import com.springmvc.SpringMVC.repository.InvoiceRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public void addProduct(@NotNull InvoiceModel invoice, ProductModel newProduct) {

        Set<ProductModel> oldProducts = invoice.getInvoiceProducts();

        if (oldProducts.contains(newProduct)) {
            oldProducts.remove(newProduct);
            newProduct.setAmount(newProduct.getAmount() + 1);
            oldProducts.add(newProduct);
        } else {
            oldProducts.add(newProduct);
        }
        invoice.setInvoiceProducts(oldProducts);
        invoiceRepository.save(invoice);
    }
}
