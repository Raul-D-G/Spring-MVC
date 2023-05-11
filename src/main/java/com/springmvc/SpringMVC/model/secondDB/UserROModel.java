package com.springmvc.SpringMVC.model.secondDB;

import com.springmvc.SpringMVC.constraint.ValidPassword;
import com.springmvc.SpringMVC.model.firstDB.CompanyModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users_ro")
public class UserROModel {

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
    private CompanyROModel company;

    public UserROModel() {
    }

    public UserROModel(Integer id, String userName, String password, String role, CompanyROModel company) {
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

    public CompanyROModel getCompany() {
        return company;
    }

    public void setCompany(CompanyROModel company) {
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
