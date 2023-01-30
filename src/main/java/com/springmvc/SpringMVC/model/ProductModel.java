package com.springmvc.SpringMVC.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class ProductModel {

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
    private CompanyModel productCompany;

//    @ManyToMany(mappedBy = "invoiceProducts")
//    Set<InvoiceModel> productInvoices;

    @OneToMany(mappedBy = "product")
    private List<BillingModel> billings;

    public ProductModel() {
    }

    public ProductModel(Integer id, String name, Float price, String category, CompanyModel productCompany, List<BillingModel> billings) {
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

    public CompanyModel getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(CompanyModel productCompany) {
        this.productCompany = productCompany;
    }

    public List<BillingModel> getBillings() {
        return billings;
    }

    public void setBillings(List<BillingModel> billings) {
        this.billings = billings;
    }

    public void removeBilling(BillingModel billing) {
        List<BillingModel> billings = this.getBillings();
        billings.remove(billing);
        this.setBillings(billings);
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
