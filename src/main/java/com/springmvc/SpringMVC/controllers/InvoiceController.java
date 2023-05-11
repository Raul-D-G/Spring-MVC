package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.firstDB.*;
import com.springmvc.SpringMVC.model.firstDB.ProductModel;
import com.springmvc.SpringMVC.repository.firstDB.BillingRepository;
import com.springmvc.SpringMVC.repository.firstDB.ExchangeRepository;
import com.springmvc.SpringMVC.repository.firstDB.InvoiceRepository;
import com.springmvc.SpringMVC.repository.firstDB.UserRepository;
import com.springmvc.SpringMVC.repository.firstDB.ProductRepository;
import com.springmvc.SpringMVC.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    BillingRepository billingRepository;

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
        mav.addObject("billings", invoice.getBillings());

        return mav;
    }

    @PostMapping("/updateInvoice")
    public String updateInvoice(@ModelAttribute("newProduct") ProductModel product, @RequestParam Integer invoiceId, @RequestParam String unit, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
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
            invoiceService.addProduct(invoice, newProduct, unit, 0);
            logger.info("The following invoice has been updated " + invoice);
        } catch (Exception e) {
            logger.info(e.getMessage());
            bindingResult.rejectValue("product", "401", e.getMessage());
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
        model.addAttribute("invoice", invoice);

        return "productList";
    }

    @GetMapping("/invoices")
    public String invoices(@RequestParam String userName, final Model model) {

        UserModel user = userRepository.findUserModelByUserName(userName);

        List<InvoiceModel> invoices = invoiceRepository.findAllByInvoiceCompany(user.getCompany());

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
    public String saveInvoice(@Valid @ModelAttribute("invoice") InvoiceModel invoice, @RequestParam Integer invoiceProductId, @RequestParam(value = "unit") String unit, @RequestParam(value = "amount") Integer amount, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        System.out.println(invoice);
        ProductModel product = productRepository.findById(invoiceProductId).get();

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

            if (invoice.getId() != null) {
                invoiceService.addProduct(invoice, product, unit, amount);

            } else {

                BillingModel billingModel = new BillingModel();
                billingModel.setInvoice(invoice);
                billingModel.setProduct(product);
                billingModel.setUnit(unit);
                billingModel.setAmount(amount);

                invoice.getBillings().add(billingModel);

                InvoiceModel savedInvoice = invoiceRepository.save(invoice);
                logger.info("The following invoice has been added " + savedInvoice);

                BillingId id = new BillingId(savedInvoice.getId(), invoiceProductId);
                billingModel.setId(id);
                billingRepository.save(billingModel);
            }

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

        try {
            billingRepository.deleteAll(invoice.getBillings());
            invoiceRepository.deleteById(invoiceId);
        } catch (Exception e) {
            // log the exception
            logger.error("Error deleting invoice and its billings: " + e.getMessage());
        }

        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/invoices";
    }
}
