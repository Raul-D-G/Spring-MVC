package com.springmvc.SpringMVC.services;

import com.springmvc.SpringMVC.exception.UserAlreadyExistException;
import com.springmvc.SpringMVC.model.UserModel;
import com.springmvc.SpringMVC.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(UserModel user) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if (checkIfUserExist(user.getUserName())) {
            throw new UserAlreadyExistException("User already exists for this User Name");
        }
        encodePassword(user);
        user.setRole("USER"); //ROLE -= USER by FORCE
        userRepository.save(user);
    }

    private void encodePassword(UserModel userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
    }

    @Override
    public boolean checkIfUserExist(String userName) {
        return userRepository.findUserModelByUserName(userName) != null;
    }
}
