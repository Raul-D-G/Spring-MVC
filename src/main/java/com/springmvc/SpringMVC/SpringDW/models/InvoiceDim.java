package com.springmvc.SpringMVC.SpringDW.models;

import com.springmvc.SpringMVC.model.CompanyModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "invoices_dim")
public class InvoiceDim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Invoice series can not be empty!")
    @NotNull
    @Size(min = 2, message = "Invoice series can not be shorter than 2 characters")
    private String series;

    @Column(name = "invoice_number")
    @NotNull(message = "Invoice number is required!")
    @Min(0)
    private Integer number;

    @OneToOne(mappedBy = "invoice")
    private TimeDim time;

    @Column
    @NotNull(message = "Invoice tva can not be 0!")
    @Min(1)
    private Integer tva;

    @Column(name = "nr_products")
    private Integer nrProducts;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyDim invoiceCompany;

    @Column(name = "invoice_price")
    @NotNull(message = "Product price can not be 0")
    @Min(0)
    private Float invoicePrice;

    @Column(name = "price_with_tax")
    @NotNull(message = "Product price can not be 0")
    @Min(0)
    private Float tvaPrice;

    public InvoiceDim() {
    }

    public InvoiceDim(Integer id, String series, Integer number, TimeDim time, Integer tva, Integer nrProducts, CompanyDim invoiceCompany, Float invoicePrice, Float tvaPrice) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.time = time;
        this.tva = tva;
        this.nrProducts = nrProducts;
        this.invoiceCompany = invoiceCompany;
        this.invoicePrice = invoicePrice;
        this.tvaPrice = tvaPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public TimeDim getTime() {
        return time;
    }

    public void setTime(TimeDim time) {
        this.time = time;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public Integer getNrProducts() {
        return nrProducts;
    }

    public void setNrProducts(Integer nrProducts) {
        this.nrProducts = nrProducts;
    }

    public CompanyDim getInvoiceCompany() {
        return invoiceCompany;
    }

    public void setInvoiceCompany(CompanyDim invoiceCompany) {
        this.invoiceCompany = invoiceCompany;
    }

    public Float getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(Float invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public Float getTvaPrice() {
        return tvaPrice;
    }

    public void setTvaPrice(Float tvaPrice) {
        this.tvaPrice = tvaPrice;
    }

    @Override
    public String toString() {
        return "InvoiceDim{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", time=" + time +
                ", tva=" + tva +
                ", nrProducts=" + nrProducts +
                ", invoiceCompany=" + invoiceCompany +
                ", invoicePrice=" + invoicePrice +
                ", tvaPrice=" + tvaPrice +
                '}';
    }
}
