package com.springmvc.SpringMVC.model.firstDB;

import com.springmvc.SpringMVC.model.firstDB.ProductModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "companies")
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Company name can not be empty")
    @Size(min = 5, message = "Company name can not be shorter than 5 characters")
    @NotNull
    private String name;

    @Column
    @Email
    @NotNull
    private String mail;

    @Column
    @NotEmpty(message = "Company cui can not be empty")
    @Size(min = 5, message = "Company cui can not be shorter than 5 characters")
    @NotNull
    private String cui;

    @Column(name = "bank_account")
    @NotEmpty(message = "Company bank account can not be empty")
    @NotNull
    @Size(min = 5, message = "Company bank account can not be shorter than 5 characters")
    private String bankAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @OneToMany(mappedBy = "company")
    private Set<ClientModel> clients;

    @OneToMany(mappedBy = "productCompany")
    private Set<ProductModel> products;

    @OneToMany(mappedBy = "invoiceCompany")
    private Set<InvoiceModel> companyInvoices;

    public CompanyModel(Integer id, String name, String mail, String cui, String bankAccount, UserModel user, Set<ClientModel> clients, Set<ProductModel> products, Set<InvoiceModel> companyInvoices) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.cui = cui;
        this.bankAccount = bankAccount;
        this.user = user;
        this.clients = clients;
        this.products = products;
        this.companyInvoices = companyInvoices;
    }

    public CompanyModel(UserModel user) {
        this.user = user;
    }

    public CompanyModel() {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<ClientModel> getClients() {
        return clients;
    }

    public void setClients(Set<ClientModel> clients) {
        this.clients = clients;
    }

    public void addClient(ClientModel newClient) {
        Set<ClientModel> oldClients = this.clients;
        oldClients.add(newClient);
        setClients(oldClients);
    }

    public Set<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductModel> products) {
        this.products = products;
    }

    public void addProduct(ProductModel product) {
        Set<ProductModel> oldProducts = this.products;
        oldProducts.add(product);
        setProducts(oldProducts);
    }

    public Set<InvoiceModel> getCompanyInvoices() {
        return companyInvoices;
    }

    public void setCompanyInvoices(Set<InvoiceModel> companyInvoices) {
        this.companyInvoices = companyInvoices;
    }

    public void addInvoice(InvoiceModel newInvoice) {
        Set<InvoiceModel> oldInvoices = getCompanyInvoices();
        oldInvoices.add(newInvoice);
        setCompanyInvoices(oldInvoices);
    }

    @Override
    public String toString() {
        return "CompanyModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", cui='" + cui + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
//                ", user=" + user +
                '}';
    }
}
