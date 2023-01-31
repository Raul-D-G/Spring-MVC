package com.springmvc.SpringMVC.SpringDW.models;

import com.springmvc.SpringMVC.model.BillingModel;
import com.springmvc.SpringMVC.model.CompanyModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private CompanyDim company;

    @OneToMany(mappedBy = "billingProduct")
    private List<BillingsFact> billings;


    public ProductDim() {
    }

    public ProductDim(int id, String name, Float price, String category, CompanyDim company, List<BillingsFact> billings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.company = company;
        this.billings = billings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public CompanyDim getCompany() {
        return company;
    }

    public void setCompany(CompanyDim company) {
        this.company = company;
    }

    public List<BillingsFact> getBillings() {
        return billings;
    }

    public void setBillings(List<BillingsFact> billings) {
        this.billings = billings;
    }

    @Override
    public String toString() {
        return "ProductDim{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", company=" + company +
                '}';
    }
}
