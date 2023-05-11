package com.springmvc.SpringMVC.model.secondDB;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BILLINGS_RO")
public class BillingROModel {
    @EmbeddedId
    private BillingROId id;

    @MapsId("invoiceId")
    @JoinColumn(name = "INVOICE_ID", nullable = false)
    private Integer invoice_id;


    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id", nullable = false)
    private ProductROModel product;

    @Column
    @NotNull(message = "The amount of products for the billing can not be 0")
    @Min(value = 1, message = "At least one product is required")
    private Integer amount;

    @Column
    @NotEmpty(message = "The product unit on the billing can not be empty.")
    @NotNull
    @Size(min = 2, message = "Product unit can not be shorter than 2 characters")
    private String unit;

    public BillingROModel() {
    }


    public BillingROModel(BillingROId id, Integer invoice, ProductROModel product, Integer amount, String unit) {
        this.id = id;
        this.invoice_id = invoice;
        this.product = product;
        this.amount = amount;
        this.unit = unit;
    }

    public BillingROId getId() {
        return id;
    }

    public void setId(BillingROId id) {
        this.id = id;
    }

    public Integer getInvoice() {
        return invoice_id;
    }

    public void setInvoice(Integer invoice) {
        this.invoice_id = invoice;
    }

    public ProductROModel getProduct() {
        return product;
    }

    public void setProduct(ProductROModel product) {
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
        return "BillingROModel{" +
                ", productId=" + product.getId() +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                '}';
    }
}