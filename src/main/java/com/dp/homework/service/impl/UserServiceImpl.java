package com.dp.homework.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.dp.homework.entity.User;
import com.dp.homework.mapper.UserMapper;
import com.dp.homework.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dp.homework.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv-success
 * @since 2019-08-25
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {


    @Override
    public void join(Map<String, Object> map, String field) {
        Map<String,Object> joinColums = new HashMap<>();
        //字段的值
        String linkFieldValue = map.get(field).toString();
        User u = this.getById(linkFieldValue);
        joinColums.put("username",u.getUsername());
        joinColums.put("email",u.getEmail());
        joinColums.put("avatar",u.getAvatar());
        joinColums.put("id",u.getId());
        map.put("author",joinColums);
    }

    @Override
    @Transactional
    public R saveUser(User user) {
        if(StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword()) ||
        StringUtils.isEmpty(user.getUsername())){
            return R.failed("必要字段不能为空");
        }

        User po = this.getOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        if (po != null){
            return R.failed("邮箱已被注册");
        }
        po = new User();
        BeanUtils.copyProperties(user,po);
        po.setPassword(SecureUtil.md5(user.getPassword()));
        po.setCreated(new Date());
        po.setAvatar("images/avatar/default.png");
        po.setPoint(0);



        return this.save(po)?R.ok(""):R.failed("注册失败");
    }

    @Override
    public AccountProfile login(String email, String password) {

        log.info("----------------------->进入登录判断");
        User user = this.getOne(new QueryWrapper<User>().eq("email", email));
        if(user == null){
            throw new UnknownAccountException("用户不存在");
        }
        if(!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("密码错误");
        }

        //更新最后登录时间
        user.setLasted(new Date());
        user.updateById();
        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user,accountProfile);

        //其他操作
        return accountProfile;
    }
}
