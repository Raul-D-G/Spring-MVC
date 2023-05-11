package com.springmvc.SpringMVC.repository.firstDB;

import com.springmvc.SpringMVC.model.firstDB.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findUserModelByUserName(String userName);
}
