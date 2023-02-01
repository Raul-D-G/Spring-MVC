package com.springmvc.SpringMVC.SpringDW.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "clients_dim")
public class ClientDim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Client name can not be empty")
    @Size(min = 3, message = "Client name can not be shorter than 3 characters")
    private String name;

    @Column
    @NotEmpty(message = "Client cui can not be empty")
    @Size(min = 5, message = "Client cui can not be shorter than 5 characters")
    private String cui;


    @Column(name = "nr_invoices")
    private Integer nrInvoices;


    @OneToMany(mappedBy = "billingClient")
    private Set<BillingsFact> clientBillings;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CompanyDim clientCompany;

    public ClientDim() {

    }

    public ClientDim(Integer id, String name, String cui, Integer nrInvoices, Set<BillingsFact> clientBillings, CompanyDim clientCompany) {
        this.id = id;
        this.name = name;
        this.cui = cui;
        this.nrInvoices = nrInvoices;
        this.clientBillings = clientBillings;
        this.clientCompany = clientCompany;
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

    public Set<BillingsFact> getClientBillings() {
        return clientBillings;
    }

    public void setClientBillings(Set<BillingsFact> clientBillings) {
        this.clientBillings = clientBillings;
    }

    public CompanyDim getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(CompanyDim clientCompany) {
        this.clientCompany = clientCompany;
    }

    @Override
    public String toString() {
        return "ClientDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cui='" + cui + '\'' +
                ", nrInvoices=" + nrInvoices +
                '}';
    }
}
