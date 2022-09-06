package com.springmvc.SpringMVC.model;


import com.springmvc.SpringMVC.constraint.ValidDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "invoices")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Invoice series can not be empty!")
    @Size(min = 2, message = "Product unit can not be shorter than 2 characters")
    private String series;

    @Column
    @NotNull(message = "Invoice number is required!")
    private Integer number;

    @Column(name = "issue_date")
    @ValidDate
    private String issueDate;

    @Column(name = "payment_deadline")
    @ValidDate
    private String paymentDeadline;

    @Column
    @NotEmpty
    private String delegate;

    @Column
    @NotNull
    private Integer tva;
}
