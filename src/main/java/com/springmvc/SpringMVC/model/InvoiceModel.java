package com.springmvc.SpringMVC.model;


import com.springmvc.SpringMVC.constraint.ValidDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "invoices")
public class InvoiceModel {

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

    @Column(name = "invoice_delegate")
    @NotNull
    @NotEmpty(message = "Invoice delegate can not be empty!")
    private String delegate;

    @Column
    @NotNull(message = "Invoice tva can not be 0!")
    @Min(1)
    private Integer tva;

    @ManyToOne
    @JoinColumn(name = "exchange_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice exchange is required!")
    private ExchangeModel invoiceExchange;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice client is required!")
    private ClientModel invoiceClient;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyModel invoiceCompany;

    @OneToMany(mappedBy = "invoice")
    private List<BillingModel> billings;

    public InvoiceModel(Integer id, String series, Integer number, Date issueDate, Date paymentDeadline, String delegate, Integer tva, ExchangeModel invoiceExchange, ClientModel invoiceClient, CompanyModel invoiceCompany, List<BillingModel> billings) {
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
        this.billings = billings;
    }

    public InvoiceModel() {
        this.billings = new ArrayList<BillingModel>();
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

    public List<BillingModel> getBillings() {
        return billings;
    }

    public void setBillings(List<BillingModel> billings) {
        this.billings = billings;
    }

    public Set<ProductModel> getInvoiceProducts() {
        Set<ProductModel> invoiceProducts = new HashSet<>();

        List<BillingModel> billings = getBillings();
        if (billings != null) {
            for (BillingModel billing : billings) {
                ProductModel product = billing.getProduct();
                invoiceProducts.add(product);
            }
        }
        return invoiceProducts;
    }

    public Float getPrice() {
        float totalPrice = 0;

        for (BillingModel billingModel : getBillings()) {
            ProductModel product = billingModel.getProduct();
            totalPrice += billingModel.getAmount() * product.getPrice();
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

    public void addNewProduct(ProductModel product, String unit, int amount) {
        BillingModel billing = new BillingModel();
        billing.setUnit(unit);
        billing.setAmount(amount);
        billing.setInvoice(this);
        billing.setProduct(product);
        billings.add(billing);
    }

    public void removeBilling(BillingModel billing) {
        List<BillingModel> billings = this.getBillings();
        billings.remove(billing);
        this.setBillings(billings);
    }

    public int diff() {
        long diff = this.getPaymentDeadline().getTime() - this.getIssueDate().getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "InvoiceModel{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", issueDate=" + issueDate +
                ", paymentDeadline=" + paymentDeadline +
                ", delegate='" + delegate + '\'' +
                ", tva=" + tva +
                ", invoiceExchange=" + invoiceExchange +
                ", invoiceClient=" + invoiceClient +
                ", invoiceCompany=" + invoiceCompany +
                '}';
    }
}
