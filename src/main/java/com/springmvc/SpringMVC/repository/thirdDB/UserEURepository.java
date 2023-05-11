package com.springmvc.SpringMVC.repository.thirdDB;

import com.springmvc.SpringMVC.model.thirdDB.UserEUModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEURepository extends JpaRepository<UserEUModel, Integer> {
    UserEUModel findUserEUModelByUserName(String userName);
}
