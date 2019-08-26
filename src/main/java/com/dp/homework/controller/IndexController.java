package com.dp.homework.controller;

import com.dp.homework.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author dp
 * @create 2019-08-23 12:30
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        log.info("-------------------------------->这是首页");
        List<Map<String, Object>> levelPosts = postService.getTopPost();
        model.addAttribute("levelPosts",levelPosts);
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