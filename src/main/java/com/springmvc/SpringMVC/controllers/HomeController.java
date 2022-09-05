package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    HttpSession session; //autowiring session


//    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String hello(ModelMap model) {
//        logger.debug("Method hello");
//        return "hello";
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index() {
//        logger.debug("Method index");
//        return "redirect:/hello";
//    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/home")
    public String home(final Model model) {
        return "home";
    }
}
