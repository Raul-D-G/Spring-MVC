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


    @OneToMany(mappedBy = "clientCompany")
//    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Set<ClientDim> companyClients;

    @OneToMany(mappedBy = "billingCompany")
//    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Set<BillingsFact> companyBillings;

    @OneToMany(mappedBy = "productCompany")
//    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Set<ProductDim> companyProducts;

    @OneToMany(mappedBy = "invoiceCompany")
//    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Set<InvoiceDim> companyInvoices;


    public CompanyDim() {
    }

    public CompanyDim(Integer id, String name, String cui, Integer nrInvoices, Set<ClientDim> companyClients, Set<BillingsFact> companyBillings, Set<ProductDim> companyProducts, Set<InvoiceDim> companyInvoices) {
        this.id = id;
        this.name = name;
        this.cui = cui;
        this.nrInvoices = nrInvoices;
        this.companyClients = companyClients;
        this.companyBillings = companyBillings;
        this.companyProducts = companyProducts;
        this.companyInvoices = companyInvoices;
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

    public Integer getNrInvoices() {
        return nrInvoices;
    }

    public void setNrInvoices(Integer nrInvoices) {
        this.nrInvoices = nrInvoices;
    }

    public Set<ClientDim> getCompanyClients() {
        return companyClients;
    }

    public void setCompanyClients(Set<ClientDim> companyClients) {
        this.companyClients = companyClients;
    }

    public Set<BillingsFact> getCompanyBillings() {
        return companyBillings;
    }

    public void setCompanyBillings(Set<BillingsFact> companyBillings) {
        this.companyBillings = companyBillings;
    }

    public Set<ProductDim> getCompanyProducts() {
        return companyProducts;
    }

    public void setCompanyProducts(Set<ProductDim> companyProducts) {
        this.companyProducts = companyProducts;
    }

    public Set<InvoiceDim> getCompanyInvoices() {
        return companyInvoices;
    }

    public void setCompanyInvoices(Set<InvoiceDim> companyInvoices) {
        this.companyInvoices = companyInvoices;
    }

    @Override
    public String toString() {
        return "CompanyDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cui='" + cui + '\'' +
                ", nrInvoices=" + nrInvoices +
                '}';
    }
}
