package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.ClientModel;
import com.springmvc.SpringMVC.model.CompanyModel;
import com.springmvc.SpringMVC.model.UserModel;
import com.springmvc.SpringMVC.repository.ClientRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    HttpSession session; //autowiring session
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);


    @GetMapping("/clients")
    public String home(@RequestParam String userName, final Model model) {

        UserModel user = userRepository.findUserModelByUserName(userName);

        List<ClientModel> clients = clientRepository.findAllByCompany(user.getCompany());

        logger.info("The following clients were found: " + clients);

        model.addAttribute("clients", clients);
        model.addAttribute("user", user);

        return "clientList";
    }

    @GetMapping("/addClientForm")
    public ModelAndView addClientForm(@RequestParam String userName) {
        ModelAndView mav = new ModelAndView("add-client-form");

        UserModel user = userRepository.findUserModelByUserName(userName);

        CompanyModel company = companyRepository.findById(user.getCompany().getId()).get();

        ClientModel newClient = new ClientModel();
        newClient.setCompany(company);

        company.addClient(newClient);

        mav.addObject("client", newClient);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/saveClient")
    public String saveClient(@Valid @ModelAttribute("client") ClientModel client, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            return "add-client-form";
        }
        try {
            clientRepository.save(client);
            logger.info("The following client has been added " + client + "by company: " + client.getCompany().getName());
        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("client", client);
            return "add-client-form";
        }
        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/clients";
    }

    @GetMapping("/showClientUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Integer clientId) {
        ModelAndView mav = new ModelAndView("add-client-form");
        UserModel user = userRepository.findUserModelByUserName(session.getAttribute("userName").toString());
        ClientModel client = clientRepository.findById(clientId).get();
        mav.addObject("client", client);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/deleteClient")
    public String deleteClient(@RequestParam Integer clientId, RedirectAttributes redirectAttributes) {
        ClientModel client = clientRepository.findById(clientId).get();
        client.setCompany(null);
        clientRepository.deleteById(clientId);
        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());
        return "redirect:/clients";
    }
}
