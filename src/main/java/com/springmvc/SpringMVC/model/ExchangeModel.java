package com.springmvc.SpringMVC.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "exchange")
public class ExchangeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Currency name can not be empty")
    @NotNull
    @Size(min = 3, max = 5, message = "Currency name can not be shorter than 3 characters and longer than 5")
    private String currency;

    @Column
    @NotNull(message = "Currency rate can not be 0")
    @Min(1)
    private Float rates;

    @OneToMany(mappedBy = "invoiceExchange")
    private Set<InvoiceModel> exchangeInvoices;

    public ExchangeModel(Integer id, String currency, Float rates, Set<InvoiceModel> exchangeInvoices) {
        this.id = id;
        this.currency = currency;
        this.rates = rates;
        this.exchangeInvoices = exchangeInvoices;
    }

    public ExchangeModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getRates() {
        return rates;
    }

    public void setRates(Float rates) {
        this.rates = rates;
    }

    public Set<InvoiceModel> getExchangeInvoices() {
        return exchangeInvoices;
    }

    public void setExchangeInvoices(Set<InvoiceModel> exchangeInvoices) {
        this.exchangeInvoices = exchangeInvoices;
    }

    @Override
    public String toString() {
        return "ExchangeModel{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", rates=" + rates +
                '}';
    }
}
