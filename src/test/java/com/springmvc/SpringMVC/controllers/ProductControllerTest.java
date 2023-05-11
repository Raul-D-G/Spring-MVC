package com.springmvc.SpringMVC.controllers;

import com.springmvc.SpringMVC.model.firstDB.UserModel;
import com.springmvc.SpringMVC.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@SpringBootTest
class ProductControllerTest {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(get("/").accept(MediaType.TEXT_HTML))
                .apply(sharedHttpSession())
                .alwaysExpect(status().isOk())
                .alwaysExpect(content().contentType("text/html;charset=UTF-8"))
                .build();
    }

    @Test
    void products() throws Exception {
        String userName = "admin@example.com";
        MockHttpSession session = new MockHttpSession();

        UserModel user = new UserModel();
        user.setUserName("admin@example.com");
        user.setPassword("password");
//        userService.register(user);

        session.setAttribute("userName", user.getUserName());


        this.mockMvc.perform(MockMvcRequestBuilders.get("/products").queryParam("userName", userName).session(session))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("productList"))
                .andExpect(MockMvcResultMatchers.status().isOk());

//        MvcResult result = this.mockMvc.perform(get("/products").queryParam("userName", userName).accept(MediaType.TEXT_HTML)).andReturn();
//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void addProductForm() throws Exception {
        String userName = "asdr";
        MockHttpSession session = new MockHttpSession();


        session.setAttribute("userName", userName);


        this.mockMvc.perform(MockMvcRequestBuilders.get("/addProductForm").queryParam("userName", userName).session(session))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("add-product-form"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveProduct() throws Exception {
//        String userName = "asdr";
//        MockHttpSession session = new MockHttpSession();
//
//
//        session.setAttribute("userName", userName);
//
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/addProductForm").queryParam("userName", userName).session(session))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.view().name("add-product-form"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void showUpdateForm() {
    }

    @Test
    void deleteProduct() {
    }
}