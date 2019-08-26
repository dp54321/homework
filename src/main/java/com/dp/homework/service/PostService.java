package com.dp.homework.service;

import com.dp.homework.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2019-08-25
 */
public interface PostService extends IService<Post> {


    List<Map<String,Object>> getTopPost();


}
