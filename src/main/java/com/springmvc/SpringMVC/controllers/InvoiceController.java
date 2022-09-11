package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.*;
import com.springmvc.SpringMVC.repository.ExchangeRepository;
import com.springmvc.SpringMVC.repository.InvoiceRepository;
import com.springmvc.SpringMVC.repository.ProductRepository;
import com.springmvc.SpringMVC.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class InvoiceController {
    @Autowired
    HttpSession session;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);


    @GetMapping("/addInvoiceProductsForm")
    public ModelAndView addInvoiceProductsForm(@RequestParam Integer invoiceId) {
        ModelAndView mav = new ModelAndView("add-invoiceProduct-form");


        InvoiceModel invoice = invoiceRepository.findById(invoiceId).get();
        CompanyModel company = invoice.getInvoiceCompany();
        UserModel user = invoice.getInvoiceCompany().getUser();


        ProductModel newProduct = new ProductModel();

        mav.addObject("invoice", invoice);
        mav.addObject("products", company.getProducts());
        mav.addObject("company", company);
        mav.addObject("user", user);
        mav.addObject("newProduct", newProduct);
        return mav;
    }

    @PostMapping("/updateInvoice")
    public String updateInvoice(@ModelAttribute("newProduct") ProductModel product, @RequestParam Integer invoiceId, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        InvoiceModel invoice = invoiceRepository.findById(invoiceId).get();
        ProductModel newProduct = productRepository.findById(product.getId()).get();

        if (bindingResult.hasErrors()) {
            CompanyModel company = invoice.getInvoiceCompany().getUser().getCompany();
            model.addAttribute("user", invoice.getInvoiceCompany().getUser());
            model.addAttribute("invoice", invoice);
            model.addAttribute("products", company.getProducts());
            return "add-invoiceProduct-form";
        }

        try {
            invoice.addNewProduct(newProduct);
            invoiceRepository.save(invoice);
            logger.info("The following invoice has been updated " + invoice);

        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("invoice", invoice);
            return "add-invoice-form";
        }

        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/invoices";
    }


    @GetMapping("/invoiceProducts")
    public String invoiceProducts(@RequestParam Integer invoiceId, final Model model) {

        InvoiceModel invoice = invoiceRepository.findById(invoiceId).get();
        UserModel user = invoice.getInvoiceCompany().getUser();

        Set<ProductModel> products = invoice.getInvoiceProducts();

        model.addAttribute("products", products);
        model.addAttribute("user", user);

        return "productList";
    }


    @GetMapping("/invoices")
    public String invoices(@RequestParam String userName, final Model model) {

        UserModel user = userRepository.findUserModelByUserName(userName);

        List<InvoiceModel> invoices = invoiceRepository.findAllByInvoiceCompany(user.getCompany());

//        for (InvoiceModel invoice : invoices) {     }

        logger.info("The following invoices were found: " + invoices);

        model.addAttribute("invoices", invoices);
        model.addAttribute("user", user);

        return "invoiceList";
    }

    @GetMapping("/addInvoiceForm")
    public ModelAndView addInvoiceForm(@RequestParam Integer userId) {
        ModelAndView mav = new ModelAndView("add-invoice-form");

        UserModel user = userRepository.findById(userId).get();

        InvoiceModel invoice = new InvoiceModel();
        invoice.setInvoiceCompany(user.getCompany());

        CompanyModel company = user.getCompany();
        company.addInvoice(invoice);

        mav.addObject("invoice", invoice);
        mav.addObject("user", user);
        mav.addObject("clients", company.getClients());
        mav.addObject("products", company.getProducts());
        mav.addObject("exchanges", exchangeRepository.findAll());
        return mav;
    }

    @PostMapping("/saveInvoice")
    public String saveInvoice(@Valid @ModelAttribute("invoice") InvoiceModel invoice, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        System.out.println(invoice);

        if (bindingResult.hasErrors()) {
            CompanyModel company = invoice.getInvoiceCompany().getUser().getCompany();
            model.addAttribute("user", invoice.getInvoiceCompany().getUser());
            model.addAttribute("invoice", invoice);
            model.addAttribute("clients", company.getClients());
            model.addAttribute("products", company.getProducts());
            model.addAttribute("exchanges", exchangeRepository.findAll());
            return "add-invoice-form";
        }

        try {
            invoiceRepository.save(invoice);
            logger.info("The following invoice has been added " + invoice);

        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("invoice", invoice);
            return "add-invoice-form";
        }

        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/invoices";
    }

    @GetMapping("/showInvoiceUpdateForm")
    public ModelAndView showInvoiceUpdateForm(@RequestParam Integer invoiceId) {
        ModelAndView mav = new ModelAndView("add-invoice-form");
        InvoiceModel invoice = invoiceRepository.findById(invoiceId).get();
        CompanyModel company = invoice.getInvoiceCompany();
        UserModel user = company.getUser();

        mav.addObject("invoice", invoice);
        mav.addObject("user", user);
        mav.addObject("clients", company.getClients());
        mav.addObject("products", company.getProducts());
        mav.addObject("exchanges", exchangeRepository.findAll());
        return mav;
    }

    @GetMapping("/deleteInvoice")
    public String deleteInvoice(@RequestParam Integer invoiceId, RedirectAttributes redirectAttributes) {
        InvoiceModel invoice = invoiceRepository.findById(invoiceId).get();
        invoice.setInvoiceClient(null);
        invoice.setInvoiceExchange(null);
        invoice.setInvoiceProducts(null);
        invoiceRepository.deleteById(invoiceId);
        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/invoices";
    }
}
