package com.springmvc.SpringMVC.services;

import com.springmvc.SpringMVC.exception.UserAlreadyExistException;
import com.springmvc.SpringMVC.model.UserModel;

public interface IUserService {
    void register(final UserModel user) throws UserAlreadyExistException;

    boolean checkIfUserExist(final String userName);
}
