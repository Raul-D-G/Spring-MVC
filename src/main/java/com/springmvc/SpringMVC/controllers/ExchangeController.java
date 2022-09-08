package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.CompanyModel;
import com.springmvc.SpringMVC.model.ExchangeModel;
import com.springmvc.SpringMVC.model.ProductModel;
import com.springmvc.SpringMVC.model.UserModel;
import com.springmvc.SpringMVC.repository.CompanyRepository;
import com.springmvc.SpringMVC.repository.ExchangeRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class ExchangeController {

    @Autowired
    HttpSession session;
    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);


    @GetMapping("/exchanges")
    public String home(final Model model) {
        UserModel user = userRepository.findUserModelByUserName(session.getAttribute("userName").toString());

        List<ExchangeModel> exchanges = Collections.emptyList();
        exchanges = exchangeRepository.findAll();
        logger.info("The following exchange rates were found: " + exchanges);
        model.addAttribute("user", user);
        model.addAttribute("exchanges", exchanges);
        return "exchangeList";
    }

    @GetMapping("/addExchangeForm")
    public ModelAndView addExchangeForm() {
        ModelAndView mav = new ModelAndView("add-exchange-form");
        UserModel user = userRepository.findUserModelByUserName(session.getAttribute("userName").toString());
        ExchangeModel exchangeModel = new ExchangeModel();
        mav.addObject("user", user);
        mav.addObject("exchange", exchangeModel);
        return mav;
    }

    @PostMapping("/saveExchange")
    public String saveExchange(@Valid @ModelAttribute("exchange") ExchangeModel exchange, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exchange", exchange);
            UserModel user = userRepository.findUserModelByUserName(session.getAttribute("userName").toString());
            model.addAttribute("user", user);
            return "add-exchange-form";
        }

        try {

            exchangeRepository.save(exchange);
            logger.info("The following exchange has been added " + exchange);

        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("exchange", exchange);
            return "add-exchange-form";
        }

//        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/exchanges";
    }

    @GetMapping("/showExchangeUpdateForm")
    public ModelAndView showExchangeUpdateForm(@RequestParam Integer exchangeId) {
        ModelAndView mav = new ModelAndView("add-exchange-form");

        UserModel user = userRepository.findUserModelByUserName(session.getAttribute("userName").toString());

        ExchangeModel exchange = exchangeRepository.getById(exchangeId);

        mav.addObject("exchange", exchange);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/deleteExchange")
    public String deleteExchange(@RequestParam Integer exchangeId, RedirectAttributes redirectAttributes) {
//        ExchangeModel exchange = exchangeRepository.findById(exchangeId).get();
        exchangeRepository.deleteById(exchangeId);
//        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());
        return "redirect:/exchanges";
    }
}
