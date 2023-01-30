//package com.springmvc.SpringMVC.SpringDW.models;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import java.util.Set;
//
//@Entity
//@Table(name = "clients_dim")
//public class ClientsDim {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column
//    @NotEmpty(message = "Client name can not be empty")
//    @Size(min = 3, message = "Client name can not be shorter than 3 characters")
//    private String name;
//
//    @Column
//    @NotEmpty(message = "Client cui can not be empty")
//    @Size(min = 5, message = "Client cui can not be shorter than 5 characters")
//    private String cui;
//
//    @OneToMany(mappedBy = "billingClient")
//    private Set<BillingsFact> clientBillings;
//
//    public ClientsDim() {
//
//    }
//
//    public ClientsDim(Integer id, String name, String cui, Set<BillingsFact> clientBillings) {
//        this.id = id;
//        this.name = name;
//        this.cui = cui;
//        this.clientBillings = clientBillings;
//    }
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
//    public String getCui() {
//        return cui;
//    }
//
//    public void setCui(String cui) {
//        this.cui = cui;
//    }
//
//    @Override
//    public String toString() {
//        return "ClientsDim{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", cui='" + cui + '\'' +
//                '}';
//    }
//
//    public Set<BillingsFact> getClientBillings() {
//        return clientBillings;
//    }
//
//    public void setClientBillings(Set<BillingsFact> clientBillings) {
//        this.clientBillings = clientBillings;
//    }
//}