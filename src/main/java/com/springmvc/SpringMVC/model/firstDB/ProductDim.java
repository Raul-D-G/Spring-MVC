package com.springmvc.SpringMVC.model.firstDB;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "products_dim")
public class ProductDim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Product name can not be empty")
    @NotNull
    @Size(min = 3, message = "Product name can not be shorter than 3 characters")
    private String name;

    @Column
    @NotNull(message = "Product price can not be 0")
    @Min(0)
    private Float price;

    @Column
    @NotEmpty(message = "Product category can not be empty")
    @NotNull
    private String category;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CompanyDim productCompany;

    @OneToMany(mappedBy = "billingProduct")
//    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private List<BillingsFact> productBillings;


    public ProductDim() {
    }

    public ProductDim(Integer id, String name, Float price, String category, CompanyDim productCompany, List<BillingsFact> productBillings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.productCompany = productCompany;
        this.productBillings = productBillings;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CompanyDim getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(CompanyDim productCompany) {
        this.productCompany = productCompany;
    }

    public List<BillingsFact> getProductBillings() {
        return productBillings;
    }

    public void setProductBillings(List<BillingsFact> productBillings) {
        this.productBillings = productBillings;
    }

    @Override
    public String toString() {
        return "ProductDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
