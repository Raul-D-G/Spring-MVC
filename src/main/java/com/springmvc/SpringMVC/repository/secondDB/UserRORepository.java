package com.springmvc.SpringMVC.repository.secondDB;

import com.springmvc.SpringMVC.model.secondDB.UserROModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRORepository extends JpaRepository<UserROModel, Integer> {
    UserROModel findUserROModelByUserName(String userName);
}
