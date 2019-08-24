package com.dp.homework.service.impl;

import com.dp.homework.entity.Post;
import com.dp.homework.mapper.PostMapper;
import com.dp.homework.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
