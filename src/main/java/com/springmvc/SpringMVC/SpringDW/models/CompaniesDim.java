//package com.springmvc.SpringMVC.SpringDW.models;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.Set;
//
//@Entity
//@Table(name = "companies_dim")
//public class CompaniesDim {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column
//    @NotEmpty(message = "Company name can not be empty")
//    @Size(min = 5, message = "Company name can not be shorter than 5 characters")
//    @NotNull
//    private String name;
//
//    @Column
//    @NotEmpty(message = "Company cui can not be empty")
//    @Size(min = 5, message = "Company cui can not be shorter than 5 characters")
//    @NotNull
//    private String cui;
//
//    @OneToMany(mappedBy = "billingCompany")
//    private Set<BillingsFact> companyBillings;
//
//    public CompaniesDim() {
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public String getCui() {
//        return cui;
//    }
//
//    public void setCui(String cui) {
//        this.cui = cui;
//    }
//
//
//    public Set<BillingsFact> getCompanyBillings() {
//        return companyBillings;
//    }
//
//    public void setCompanyBillings(Set<BillingsFact> companyBillings) {
//        this.companyBillings = companyBillings;
//    }
//
//    @Override
//    public String toString() {
//        return "CompaniesDim{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", cui='" + cui + '\'' +
//                '}';
//    }
//}
