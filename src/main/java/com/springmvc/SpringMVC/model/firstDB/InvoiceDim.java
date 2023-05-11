package com.springmvc.SpringMVC.model.firstDB;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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


    @Column
    @NotNull(message = "Invoice tva can not be 0!")
    @Min(1)
    private Integer tva;

    @Column(name = "nr_products")
    private Integer nrProducts;


    @Column(name = "invoice_price")
    @NotNull(message = "Product price can not be 0")
    @Min(0)
    private Float invoicePrice;

    @Column(name = "price_with_tax")
    @NotNull(message = "Product price can not be 0")
    @Min(0)
    private Float tvaPrice;

    @OneToOne(mappedBy = "invoice")
    private TimeDim invoiceTime;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CompanyDim invoiceCompany;

    @OneToMany(mappedBy = "billingInvoice")
//    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Set<BillingsFact> invoiceBillings;

    public InvoiceDim() {
    }

    public InvoiceDim(Integer id, String series, Integer number, Integer tva, Integer nrProducts, Float invoicePrice, Float tvaPrice, TimeDim invoiceTime, CompanyDim invoiceCompany, Set<BillingsFact> invoiceBillings) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.tva = tva;
        this.nrProducts = nrProducts;
        this.invoicePrice = invoicePrice;
        this.tvaPrice = tvaPrice;
        this.invoiceTime = invoiceTime;
        this.invoiceCompany = invoiceCompany;
        this.invoiceBillings = invoiceBillings;
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

    public TimeDim getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(TimeDim invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public CompanyDim getInvoiceCompany() {
        return invoiceCompany;
    }

    public void setInvoiceCompany(CompanyDim invoiceCompany) {
        this.invoiceCompany = invoiceCompany;
    }

    public Set<BillingsFact> getInvoiceBillings() {
        return invoiceBillings;
    }

    public void setInvoiceBillings(Set<BillingsFact> invoiceBillings) {
        this.invoiceBillings = invoiceBillings;
    }

    @Override
    public String toString() {
        return "InvoiceDim{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", tva=" + tva +
                ", nrProducts=" + nrProducts +
                ", invoicePrice=" + invoicePrice +
                ", tvaPrice=" + tvaPrice +
                '}';
    }
}
