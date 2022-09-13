package com.springmvc.SpringMVC;

import com.springmvc.SpringMVC.controllers.ProductController;
import com.springmvc.SpringMVC.model.UserModel;
import com.springmvc.SpringMVC.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.*;

@SpringBootTest
public class ProductControllerTest {


    MockMvc mockMvc;

    @Autowired
    UserService userService;

    // 初始化MockMvc
    @BeforeEach
    void setUp(WebApplicationContext wac) {
        // 方式1：明确指定需要测试的"Controller"类
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();

        // 方式2：基于Spring容器进行配置，包含了Spring MVC环境和所有"Controller"类。
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultRequest(get("/").accept(MediaType.TEXT_HTML)) // 默认请求路径
                .apply(sharedHttpSession()) // 配置session
                .alwaysExpect(status().isOk()) // 预期响应状态码
                .alwaysExpect(content().contentType("text/html;charset=UTF-8")) // 预期内容类型
                .build();
    }

    @Test
    public void testSimpleGet() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON)) // 接受JSON格式响应消息
                .andReturn(); // 获取返回结果
        Assertions.assertEquals("OK", result.getResponse().getContentAsString());
    }

    // 访问GET接口：带URL参数
    @Test
    public void testParamGet() throws Exception {
        String userName = "asdr";
        MockHttpSession session = new MockHttpSession();

        UserModel user = new UserModel();
        user.setUserName("admin@example.com");
        user.setPassword("password");
//        userService.register(user);

        session.setAttribute("userName", user.getUserName());


        this.mockMvc.perform(MockMvcRequestBuilders.get("/products").queryParam("userName", userName).session(session))
                .andDo(print()).andExpect(MockMvcResultMatchers.view().name("productList"));

//        MvcResult result = this.mockMvc.perform(get("/products").queryParam("userName", userName).accept(MediaType.TEXT_HTML)).andReturn();
//        System.out.println(result.getResponse().getContentAsString());
    }
}

