package com.springmvc.SpringMVC.repository;

import com.springmvc.SpringMVC.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findUserModelByUserName(String userName);
}
