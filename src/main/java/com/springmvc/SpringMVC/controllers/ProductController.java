package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.exception.UserAlreadyExistException;
import com.springmvc.SpringMVC.model.ProductModel;
import com.springmvc.SpringMVC.repository.ProductRepository;
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
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    HttpSession session; //autowiring session

    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public String home(final Model model) {

        List<ProductModel> products = productRepository.findAll();

        logger.info("The following products were found: " + products);

        model.addAttribute("products", productRepository.findAll());

        return "productList";
    }

    @GetMapping("/addProductForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("add-product-form");
        ProductModel newProduct = new ProductModel();
        mav.addObject("product", newProduct);
        return mav;
    }

    @PostMapping("/saveProduct")
    public String saveEmployee(@Valid @ModelAttribute("product") ProductModel product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return "add-product-form";
        }

        try {
            productRepository.save(product);
            logger.info("The following product has been added " + product + "by user: " + session.getAttribute("userName"));

        } catch (Exception e) {
            bindingResult.rejectValue("userName", "401", e.getMessage());
            model.addAttribute("product", product);
            return "add-product-form";
        }

        return "redirect:/products";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Integer productId) {
        ModelAndView mav = new ModelAndView("add-product-form");
        ProductModel product = productRepository.findById(productId).get();
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/deleteProduct")
    public String deleteEmployee(@RequestParam Integer productId) {
        productRepository.deleteById(productId);
        return "redirect:/products";
    }
}