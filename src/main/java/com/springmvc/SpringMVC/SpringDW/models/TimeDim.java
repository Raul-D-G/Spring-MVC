//package com.springmvc.SpringMVC.SpringDW.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "time_dim")
//public class TimeDim {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Long id;
//    //...
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "billing_id", referencedColumnName = "id")
//    private BillingsFact billing;
//}
