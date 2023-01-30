package com.springmvc.SpringMVC.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BILLINGS")
public class BillingModel {
    @EmbeddedId
    private BillingId id;

    @MapsId("invoiceId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private InvoiceModel invoice;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductModel product;

    @Column
    @NotNull(message = "The amount of products for the billing can not be 0")
    @Min(value = 1, message = "At least one product is required")
    private Integer amount;

    @Column
    @NotEmpty(message = "The product unit on the billing can not be empty.")
    @NotNull
    @Size(min = 2, message = "Product unit can not be shorter than 2 characters")
    private String unit;

    public BillingModel() {
    }


    public BillingModel(BillingId id, InvoiceModel invoice, ProductModel product, Integer amount, String unit) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.amount = amount;
        this.unit = unit;
    }

    public BillingId getId() {
        return id;
    }

    public void setId(BillingId id) {
        this.id = id;
    }

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "BillingModel{" +
                "invoiceId=" + invoice.getId() +
                ", productId=" + product.getId() +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                '}';
    }
}