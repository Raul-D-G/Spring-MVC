package com.springmvc.SpringMVC.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Product name can not be empty")
    @Size(min = 3, message = "Product name can not be shorter than 3 characters")
    private String name;

    @Column
    @NotNull(message = "Product price can not be 0")
    @Min(0)
    private Float price;

    @Column
    @NotNull(message = "Product amount can not be 0")
    @Min(value = 1, message = "At least one product is required")
    private Integer amount;

    @Column
    @NotEmpty(message = "Product unit can not be empty")
    @Size(min = 2, message = "Product unit can not be shorter than 2 characters")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyModel productCompany;

    public ProductModel() {
    }

    public ProductModel(Integer id, String name, Float price, Integer amount, String unit, CompanyModel productCompany) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.unit = unit;
        this.productCompany = productCompany;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public CompanyModel getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(CompanyModel productCompany) {
        this.productCompany = productCompany;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                '}';
    }
}

