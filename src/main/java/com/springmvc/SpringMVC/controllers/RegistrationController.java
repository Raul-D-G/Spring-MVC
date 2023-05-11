package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.exception.UserAlreadyExistException;
import com.springmvc.SpringMVC.model.firstDB.UserModel;
import com.springmvc.SpringMVC.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("userData", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(@Valid @ModelAttribute("userData") UserModel userData, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userData", userData);
            return "/register";
        }
        try {
            userService.register(userData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("userData", userData);
            return "register";
        }
        return "redirect:/login";
    }
}