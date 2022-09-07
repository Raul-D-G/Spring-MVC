package com.springmvc.SpringMVC.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class ClientModel {

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

    @Column(name = "bank_account")
    @NotEmpty(message = "Client bank account can not be empty")
    @Size(min = 5, message = "Client bank account can not be shorter than 5 characters")
    private String bankAccount;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyModel company;

    public ClientModel(Integer id, String name, String cui, String bankAccount, CompanyModel company) {
        this.id = id;
        this.name = name;
        this.cui = cui;
        this.bankAccount = bankAccount;
        this.company = company;
    }

    public ClientModel() {
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cui='" + cui + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }
}