package com.springmvc.SpringMVC.SpringDW.models;

import com.springmvc.SpringMVC.model.CompanyModel;
import com.springmvc.SpringMVC.model.UserModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "time_dim")
public class TimeDim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String year;

    @Column
    private String quarter;

    @Column
    private String month;

    @Column
    private String day;


    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private InvoiceDim invoice;

    @OneToMany(mappedBy = "billingTime")
//    @JoinColumn(name = "time_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private List<BillingsFact> timeBillings;

    public TimeDim() {
    }

    public TimeDim(Integer id, String year, String quarter, String month, String day, InvoiceDim invoice, List<BillingsFact> timeBillings) {
        this.id = id;
        this.year = year;
        this.quarter = quarter;
        this.month = month;
        this.day = day;
        this.invoice = invoice;
        this.timeBillings = timeBillings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public InvoiceDim getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDim invoice) {
        this.invoice = invoice;
    }

    public List<BillingsFact> getTimeBillings() {
        return timeBillings;
    }

    public void setTimeBillings(List<BillingsFact> timeBillings) {
        this.timeBillings = timeBillings;
    }

    @Override
    public String toString() {
        return "TimeDim{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", quarter='" + quarter + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
