package com.springmvc.SpringMVC.model.thirdDB;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "companies_eu")
public class CompanyEUModel {

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
    private UserEUModel user;


    @OneToMany(mappedBy = "productCompany")
    private Set<ProductEUModel> products;


    public CompanyEUModel(Integer id, String name, String mail, String cui, String bankAccount, UserEUModel user, Set<ProductEUModel> products) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.cui = cui;
        this.bankAccount = bankAccount;
        this.user = user;
        this.products = products;
    }

    public CompanyEUModel(UserEUModel user) {
        this.user = user;
    }

    public CompanyEUModel() {
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

    public UserEUModel getUser() {
        return user;
    }

    public void setUser(UserEUModel user) {
        this.user = user;
    }


    public Set<ProductEUModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEUModel> products) {
        this.products = products;
    }

    public void addProduct(ProductEUModel product) {
        Set<ProductEUModel> oldProducts = this.products;
        oldProducts.add(product);
        setProducts(oldProducts);
    }


    @Override
    public String toString() {
        return "CompanyEUModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", cui='" + cui + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
//                ", user=" + user +
                '}';
    }
}
