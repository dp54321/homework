package com.dp.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dp
 * @create 2019-08-23 12:30
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        log.info("-------------------------------->这是首页");
        return "index";
    }

    @GetMapping("/showlogin")
    public String showLogin() {
        log.info("-------------------------------->这是首页");
        return "auth/login";
    }


    @GetMapping("/showregister")
    public String showRegister() {
        log.info("-------------------------------->这是首页");
        return "auth/register";
    }

    @GetMapping("/showcenter")
    public String showCenter() {
        log.info("-------------------------------->这是首页");
        return "user/center";
    }

}