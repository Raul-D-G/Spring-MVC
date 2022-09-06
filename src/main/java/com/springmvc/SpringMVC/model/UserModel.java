package com.springmvc.SpringMVC.model;

import com.springmvc.SpringMVC.constraint.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "username")
    @NotEmpty(message = "User name can not be empty")
    @Size(min = 4, message = "User name can not be shorter than 4 characters")
    private String userName;

    @Column(nullable = false)
    @NotEmpty(message = "Password can not be empty")
    @ValidPassword
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToOne(mappedBy = "user")
    private CompanyModel company;

    public UserModel() {
    }

    public UserModel(Integer id, String userName, String password, String role, CompanyModel company) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", company=" + company +
                '}';
    }
}
