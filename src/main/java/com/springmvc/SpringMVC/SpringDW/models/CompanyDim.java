package com.springmvc.SpringMVC.SpringDW.models;

import com.springmvc.SpringMVC.model.ProductModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "companies_dim")
public class CompanyDim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Company name can not be empty")
    @Size(min = 5, message = "Company name can not be shorter than 5 characters")
    @NotNull
    private String name;

    @Column
    @NotEmpty(message = "Company cui can not be empty")
    @Size(min = 5, message = "Company cui can not be shorter than 5 characters")
    @NotNull
    private String cui;

    @Column(name = "nr_invoices")
    private Integer nrInvoices;


    @OneToMany(mappedBy = "company")
    private Set<ClientDim> clientsDim;

    @OneToMany(mappedBy = "billingCompany")
    private Set<BillingsFact> companyBillings;

    @OneToMany(mappedBy = "company")
    private Set<ProductDim> products;

    @OneToMany(mappedBy = "invoiceCompany")
    private Set<InvoiceDim> invoices;


    public CompanyDim() {
    }

    public CompanyDim(Integer id, String name, String cui, Integer nrInvoices, Set<BillingsFact> companyBillings) {
        this.id = id;
        this.name = name;
        this.cui = cui;
        this.nrInvoices = nrInvoices;
        this.companyBillings = companyBillings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }


    public Set<BillingsFact> getCompanyBillings() {
        return companyBillings;
    }

    public void setCompanyBillings(Set<BillingsFact> companyBillings) {
        this.companyBillings = companyBillings;
    }

    public Integer getNrInvoices() {
        return nrInvoices;
    }

    public void setNrInvoices(Integer nrInvoices) {
        this.nrInvoices = nrInvoices;
    }

    @Override
    public String toString() {
        return "CompaniesDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cui='" + cui + '\'' +
                ", nrInvoices=" + nrInvoices +
                '}';
    }
}
