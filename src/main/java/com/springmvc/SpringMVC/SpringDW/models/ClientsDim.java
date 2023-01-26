package com.springmvc.SpringMVC.SpringDW.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients_dim")
public class ClientsDim {

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

    public ClientsDim() {

    }

    public ClientsDim(Integer id, String name, String cui, String bankAccount) {
        this.id = id;
        this.name = name;
        this.cui = cui;
        this.bankAccount = bankAccount;
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

    @Override
    public String toString() {
        return "ClientsDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cui='" + cui + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }
}
