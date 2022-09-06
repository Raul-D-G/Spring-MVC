package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.UserModel;
import com.springmvc.SpringMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    HttpSession session; //autowiring session

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/home")
    public String home(final Model model) {

        String userName = session.getAttribute("userName").toString();

        UserModel user = userRepository.findUserModelByUserName(userName);
        model.addAttribute("user", user);

        return "home";
    }
}
