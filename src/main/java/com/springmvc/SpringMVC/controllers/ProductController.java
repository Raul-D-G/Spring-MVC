package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.firstDB.CompanyModel;
import com.springmvc.SpringMVC.model.firstDB.ProductModel;
import com.springmvc.SpringMVC.model.firstDB.UserModel;
import com.springmvc.SpringMVC.repository.firstDB.*;
import com.springmvc.SpringMVC.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    HttpSession session; //autowiring session

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    // Annotation
    @ModelAttribute("productCategory")
    public List<String> educationDetailsList() {
        return Arrays.asList(
                "Made in Romania.", "Made in UE.");
    }

    @GetMapping("/products")
    public String products(@RequestParam(value = "userName") String userName, @RequestParam(value = "page", required = false) Optional<Integer> page, @RequestParam(value = "sortBy", required = false) Optional<String> sortBy, final Model model) {

        sortBy.ifPresent(s -> model.addAttribute("sortBy", s));

        if (sortBy.isEmpty()) {
            model.addAttribute("sortBy", "price");
        }
        sortBy.ifPresent(System.out::println);

        UserModel user = userRepository.findUserModelByUserName(userName);

        List<ProductModel> products = Collections.emptyList();
        if (user.getCompany() != null) {
            products = productRepository.findAllByProductCompany(user.getCompany(), PageRequest.of(page.orElse(0), 4, Sort.Direction.ASC, sortBy.orElse("price")));

            if (products.size() == 4) {
                if (page.isPresent()) {
                    model.addAttribute("nextPage", page.get() + 1);
                } else {
                    model.addAttribute("nextPage", 1);
                }
            }

//            if (page.isEmpty()) {
//                model.addAttribute("nextPage", 0);
//            }

            logger.info("The following products of company " + user.getCompany().getName() + " were found: " + products);
        }

        model.addAttribute("products", products);
        model.addAttribute("user", user);

        return "productList";
    }

    @GetMapping("/addProductForm")
    public ModelAndView addProductForm(@RequestParam String userName) {
        ModelAndView mav = new ModelAndView("add-product-form");

        UserModel user = userRepository.findUserModelByUserName(userName);
        CompanyModel company = companyRepository.findById(user.getCompany().getId()).get();

        ProductModel newProduct = new ProductModel();
        newProduct.setProductCompany(company);

        company.addProduct(newProduct);

        mav.addObject("product", newProduct);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") ProductModel product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("user", product.getProductCompany().getUser());
            return "add-product-form";
        }

        try {
            productService.createProduct(product);
            logger.info("The following product has been added " + product + "by user: " + session.getAttribute("userName"));

        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("product", product);
            return "add-product-form";
        }
        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/products";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Integer productId) {
        ModelAndView mav = new ModelAndView("add-product-form");

        UserModel user = userRepository.findUserModelByUserName(session.getAttribute("userName").toString());

        ProductModel product = productRepository.findById(productId).get();
        mav.addObject("product", product);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Integer productId, RedirectAttributes redirectAttributes) {

        ProductModel product = productRepository.findById(productId).get();
        product.setProductCompany(null);
        product.setBillings(null);
        productRepository.deleteById(productId);
        redirectAttributes.addAttribute("userName", session.getAttribute("userName").toString());

        return "redirect:/products";
    }
}