package com.springmvc.SpringMVC.model;


import com.springmvc.SpringMVC.constraint.ValidDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Invoice series can not be empty!")
    @Size(min = 2, message = "Invoice series can not be shorter than 2 characters")
    private String series;

    @Column
    @NotNull(message = "Invoice number is required!")
    private Integer number;

    @Column(name = "issue_date")
    @ValidDate
    @NotNull(message = "Invoice issue date is required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @Column(name = "payment_deadline")
    @ValidDate
    @NotNull(message = "Invoice payment deadline is required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDeadline;

    @Column
    @NotEmpty(message = "Invoice delegate can not be empty!")
    private String delegate;

    @Column
    @NotNull(message = "Invoice tva can not be 0!")
    @Min(1)
    private Integer tva;

    @ManyToOne
    @JoinColumn(name = "exchage_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice exchange is required!")
    private ExchangeModel invoiceExchange;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice client is required!")
    private ClientModel invoiceClient;

    @ManyToOne
    @JoinColumn(name = "compnay_id", referencedColumnName = "id", nullable = false)
    private CompanyModel invoiceCompany;

    @ManyToMany
    @JoinTable(
            name = "billings",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @NotNull(message = "Invoice product is required!")
    Set<ProductModel> invoiceProducts;

//    @Column
//    @NotNull(message = "Invoice total price is required!")
//    private Float price;

    public InvoiceModel(Integer id, String series, Integer number, Date issueDate, Date paymentDeadline, String delegate, Integer tva, ExchangeModel invoiceExchange, ClientModel invoiceClient, CompanyModel invoiceCompany, Set<ProductModel> invoiceProducts) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.issueDate = issueDate;
        this.paymentDeadline = paymentDeadline;
        this.delegate = delegate;
        this.tva = tva;
        this.invoiceExchange = invoiceExchange;
        this.invoiceClient = invoiceClient;
        this.invoiceCompany = invoiceCompany;
        this.invoiceProducts = invoiceProducts;
//        this.price = price;
    }

    public InvoiceModel() {
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public String getDelegate() {
        return delegate;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public ExchangeModel getInvoiceExchange() {
        return invoiceExchange;
    }

    public void setInvoiceExchange(ExchangeModel invoiceExchange) {
        this.invoiceExchange = invoiceExchange;
    }

    public ClientModel getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(ClientModel invoiceClient) {
        this.invoiceClient = invoiceClient;
    }

    public CompanyModel getInvoiceCompany() {
        return invoiceCompany;
    }

    public void setInvoiceCompany(CompanyModel invoiceCompany) {
        this.invoiceCompany = invoiceCompany;
    }

    public Set<ProductModel> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(Set<ProductModel> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }

//    public Float getPrice() {
//        return price;
//    }
//
//    public void setPrice(Float price) {
//        this.price = price;
//    }

    public Float getPrice() {
        float totalPrice = 0;
        for (ProductModel productModel : invoiceProducts) {
            totalPrice += productModel.getPrice();
        }
        return totalPrice;
    }

    public Float getPriceWithTVA() {
        Float tvaPrice = getPrice() * getTva() / 100;
        return getPrice() + tvaPrice;
    }

    public Float getPriceInCurrency() {
        return getPrice() * invoiceExchange.getRates();
    }

    public Float getTotalPriceInCurrency() {
        return getPriceWithTVA() * invoiceExchange.getRates();
    }

    public Float getTVAPrice() {
        return getPrice() * getTva() / 100;
    }

    public void addNewProduct(ProductModel product) {
        Set<ProductModel> oldProducts = getInvoiceProducts();
        oldProducts.add(product);
        setInvoiceProducts(oldProducts);
    }

    @Override
    public String toString() {
        return "InvoiceModel{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", issueDate='" + issueDate + '\'' +
                ", paymentDeadline='" + paymentDeadline + '\'' +
                ", delegate='" + delegate + '\'' +
                ", tva=" + tva +
                ", invoiceExchange=" + invoiceExchange +
                ", invoiceClient=" + invoiceClient +
                ", invoiceCompany=" + invoiceCompany +
                ", invoiceProducts=" + invoiceProducts +
                '}';
    }
}
