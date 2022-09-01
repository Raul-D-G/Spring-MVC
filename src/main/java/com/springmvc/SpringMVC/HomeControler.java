package com.springmvc.SpringMVC;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControler {

    @GetMapping("/home")
    public String home() {
        return "This is home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is Admin";
    }
}
