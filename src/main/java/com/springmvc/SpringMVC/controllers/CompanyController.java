package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.CompanyModel;
import com.springmvc.SpringMVC.model.UserModel;
import com.springmvc.SpringMVC.repository.CompanyRepository;
import com.springmvc.SpringMVC.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CompanyController {
    @Autowired
    HttpSession session;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/addCompanyForm")
    public ModelAndView addEmployeeForm(@RequestParam Integer userId) {
        ModelAndView mav = new ModelAndView("add-company-form");

        UserModel user = userRepository.findById(userId).get();

        CompanyModel newCompany = new CompanyModel();

        newCompany.setUser(user);

        user.setCompany(newCompany);

        mav.addObject("company", newCompany);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@Valid @ModelAttribute("company") CompanyModel company, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("company", company);
            return "add-company-form";
        }

        try {

            System.out.println(company.getUser());

            companyRepository.save(company);
            logger.info("The following company has been added " + company + "by user: " + session.getAttribute("userName"));

        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("company", company);
            return "add-company-form";
        }

        return "redirect:/home";
    }

    @GetMapping("/showCompanyUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam String userName) {
        ModelAndView mav = new ModelAndView("add-company-form");
        UserModel user = userRepository.findUserModelByUserName(userName);
        CompanyModel company = companyRepository.findById(user.getCompany().getId()).get();
        mav.addObject("company", company);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/deleteCompany")
    public String deleteCompany(@RequestParam Integer companyId) {
        CompanyModel company = companyRepository.findById(companyId).get();
        company.setUser(null);
        companyRepository.deleteById(companyId);

        return "redirect:/home";
    }
}
