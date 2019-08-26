package com.dp.homework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dp.homework.entity.Post;
import com.dp.homework.mapper.PostMapper;
import com.dp.homework.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv-success
 * @since 2019-08-25
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public List<Map<String,Object>> getTopPost(){
        List<Map<String, Object>> levelPosts = listMaps(new QueryWrapper<Post>().orderByDesc("level").last("limit 5"));
        userService.join(levelPosts,"user_id");
        categoryService.join(levelPosts,"category_id");
        return levelPosts;
    }


}
