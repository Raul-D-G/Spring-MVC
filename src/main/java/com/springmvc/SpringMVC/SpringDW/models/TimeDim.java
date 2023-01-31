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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private InvoiceDim invoice;

    @OneToMany(mappedBy = "time")
    private List<BillingsFact> billings;

    public TimeDim() {
    }

    public TimeDim(Integer id, String year, String quarter, String month, String day, List<BillingsFact> billings) {
        this.id = id;
        this.year = year;
        this.quarter = quarter;
        this.month = month;
        this.day = day;
        this.billings = billings;
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

    public List<BillingsFact> getBillings() {
        return billings;
    }

    public void setBillings(List<BillingsFact> billings) {
        this.billings = billings;
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
