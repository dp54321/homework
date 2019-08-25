package com.dp.homework.controller;

import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author dp
 * @create 2019-08-25 2:25
 * 生成验证码
 */
@Controller
public class KatchaController {

    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    @Autowired
    private Producer producer;

    @GetMapping("/capthca.jpg")
    public void capthca(HttpServletResponse rep)throws Exception{
        rep.setHeader("Cache-Control","no-store,no-cache");
        rep.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //把图片验证码存到shiro的session中
        SecurityUtils.getSubject().getSession().setAttribute(KAPTCHA_SESSION_KEY,text);
        ServletOutputStream outputStream = rep.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
    }
}




