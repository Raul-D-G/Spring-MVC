package com.springmvc.SpringMVC.model.secondDB;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BillingROId implements Serializable {
    @Column(name = "INVOICE_ID", nullable = false)
    private Integer invoiceId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BillingROId(Integer invoiceId, Integer productId) {
        this.invoiceId = invoiceId;
        this.productId = productId;
    }

    public BillingROId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BillingROId entity = (BillingROId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.invoiceId, entity.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, invoiceId);
    }

    @Override
    public String toString() {
        return "BillingId{" +
                "invoiceId=" + invoiceId +
                ", productId=" + productId +
                '}';
    }
}