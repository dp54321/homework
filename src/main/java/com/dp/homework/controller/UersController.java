package com.dp.homework.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.dp.homework.entity.User;
import com.dp.homework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dp
 * @create 2019-08-25 11:25
 */
@Controller
@RequestMapping("/user")
public class UersController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/register")
    public R saveUser(User user, String vercode, String repass){

        String kaptcha_session_key = (String)SecurityUtils.getSubject().getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if(StringUtils.isEmpty(vercode) && StringUtils.isEmpty(repass)){
            return R.failed("必要字段不能为空");
        }
        if(!vercode.equalsIgnoreCase(kaptcha_session_key)){
                return R.failed("验证码不正确");
        }
        if(!repass.equals(user.getPassword())){
                return R.failed("两次密码不一致");
        }
        R r = userService.saveUser(user);


        return r;
    }


    @ResponseBody
    @PostMapping("/login")
    public R userLogin(String email,String password){
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            return  R.failed("用户名或密码不能为空");
        }

        AuthenticationToken token = new UsernamePasswordToken(email, SecureUtil.md5(password));

        try {
            //尝试登陆  会调用realm的方法
            SecurityUtils.getSubject().login(token);
        }catch (AuthenticationException e){

            if(e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException){
                return R.failed("用户名或密码错误");
            }else if(e instanceof LockedAccountException){
                return R.failed("用户被禁用");
            }else {
                return R.failed("认证失败");
            }
        }

        return R.ok("登陆成功");

    }


}
