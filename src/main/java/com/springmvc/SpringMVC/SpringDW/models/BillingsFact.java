package com.springmvc.SpringMVC.SpringDW.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "billings_fact")
public class BillingsFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_amount")
    @NotNull(message = "The amount of products for the invoice can not be 0")
    @Min(value = 1, message = "At least one product is required")
    private Integer amount;

    @Column(name = "product_unit")
    @NotEmpty(message = "The product unit on the invoice can not be empty.")
    @NotNull
    @Size(min = 2, message = "Product unit can not be shorter than 2 characters")
    private String unit;

    @Column(name = "billing_price")
    @NotNull
    private Float price;

    @Column(name = "billing_price_with_tva")
    @NotNull
    private Float priceWTva;

    @ManyToOne
    @JoinColumn(name = "exchange_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice exchange is required!")
    private ExchangeDim billingExchange;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice client is required!")
    private ClientDim billingClient;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyDim billingCompany;

    @ManyToOne
    @JoinColumn(name = "time_id", referencedColumnName = "id", nullable = false)
    private TimeDim time;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    private InvoiceDim billingInvoice;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductDim billingProduct;

    public BillingsFact() {
    }

    public BillingsFact(Integer id, Integer amount, String unit, Float price, Float priceWTva, ExchangeDim billingExchange, ClientDim billingClient, CompanyDim billingCompany, TimeDim time, InvoiceDim billingInvoice, ProductDim billingProduct) {
        this.id = id;
        this.amount = amount;
        this.unit = unit;
        this.price = price;
        this.priceWTva = priceWTva;
        this.billingExchange = billingExchange;
        this.billingClient = billingClient;
        this.billingCompany = billingCompany;
        this.time = time;
        this.billingInvoice = billingInvoice;
        this.billingProduct = billingProduct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPriceWTva() {
        return priceWTva;
    }

    public void setPriceWTva(Float priceWTva) {
        this.priceWTva = priceWTva;
    }

    public ExchangeDim getBillingExchange() {
        return billingExchange;
    }

    public void setBillingExchange(ExchangeDim billingExchange) {
        this.billingExchange = billingExchange;
    }

    public ClientDim getBillingClient() {
        return billingClient;
    }

    public void setBillingClient(ClientDim billingClient) {
        this.billingClient = billingClient;
    }

    public CompanyDim getBillingCompany() {
        return billingCompany;
    }

    public void setBillingCompany(CompanyDim billingCompany) {
        this.billingCompany = billingCompany;
    }

    public TimeDim getTime() {
        return time;
    }

    public void setTime(TimeDim time) {
        this.time = time;
    }

    public InvoiceDim getBillingInvoice() {
        return billingInvoice;
    }

    public void setBillingInvoice(InvoiceDim billingInvoice) {
        this.billingInvoice = billingInvoice;
    }

    public ProductDim getBillingProduct() {
        return billingProduct;
    }

    public void setBillingProduct(ProductDim billingProduct) {
        this.billingProduct = billingProduct;
    }

    @Override
    public String toString() {
        return "BillingsFact{" +
                "id=" + id +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", priceWTva=" + priceWTva +
                '}';
    }
}
