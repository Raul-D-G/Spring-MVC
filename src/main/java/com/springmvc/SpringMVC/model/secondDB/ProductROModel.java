package com.springmvc.SpringMVC.model.secondDB;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "products_ro")
public class ProductROModel {

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
    private CompanyROModel productCompany;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<BillingROModel> billings;

    public ProductROModel() {
    }

    public ProductROModel(Integer id, String name, Float price, String category, CompanyROModel productCompany, List<BillingROModel> billings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.productCompany = productCompany;
        this.billings = billings;
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

    public CompanyROModel getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(CompanyROModel productCompany) {
        this.productCompany = productCompany;
    }

    public List<BillingROModel> getBillings() {
        return billings;
    }

    public void setBillings(List<BillingROModel> billings) {
        this.billings = billings;
    }

    public void removeBilling(BillingROModel billing) {
        List<BillingROModel> billings = this.getBillings();
        billings.remove(billing);
        this.setBillings(billings);
    }

    @Override
    public String toString() {
        return "ProductROModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
