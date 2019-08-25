package com.dp.homework.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.dp.homework.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dp.homework.shiro.AccountProfile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2019-08-25
 */
public interface UserService extends IService<User> {

    R saveUser(User user);

    AccountProfile login(String email, String password);
}
