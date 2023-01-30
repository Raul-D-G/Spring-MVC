//package com.springmvc.SpringMVC.SpringDW.models;
//
//import com.springmvc.SpringMVC.model.ClientModel;
//import com.springmvc.SpringMVC.model.CompanyModel;
//import com.springmvc.SpringMVC.model.ExchangeModel;
//import com.springmvc.SpringMVC.model.ProductModel;
//
//import javax.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.Set;
//
//@Entity
//@Table(name = "billings_fact")
//public class BillingsFact {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column
//    @NotNull(message = "The amount of products for the invoice can not be 0")
//    @Min(value = 1, message = "At least one product is required")
//    private Integer amount;
//
//    @Column
//    @NotEmpty(message = "The product unit on the invoice can not be empty.")
//    @NotNull
//    @Size(min = 2, message = "Product unit can not be shorter than 2 characters")
//    private String unit;
//
//    @Column
//    @NotNull
//    private Float price;
//
//    @ManyToOne
//    @JoinColumn(name = "exchange_id", referencedColumnName = "id", nullable = false)
//    @NotNull(message = "Invoice exchange is required!")
//    private ExchangeDim billingExchange;
//
//    @ManyToOne
//    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
//    @NotNull(message = "Invoice client is required!")
//    private ClientModel billingClient;
//
//    @ManyToOne
//    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
//    private CompanyModel billingCompany;
//
//    @OneToOne(mappedBy = "billing")
//    private TimeDim time;
//
//    @ManyToOne
//    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
//    private CompanyModel billingInvoice;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
//    private CompanyModel billingProduct;
//
//
//}
