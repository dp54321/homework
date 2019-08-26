package com.dp.homework.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dp.homework.HomeworkApplication;
import com.dp.homework.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author dp
 * @create 2019-08-26 21:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HomeworkApplication.class})
public class PostServiceTest {

    @Autowired
    private PostService postService;


    @Test
    public void getTopPost(){
        /*List<Map<String, Object>> level = postService.listMaps(new QueryWrapper<Post>().orderByDesc("level").last("limit 5"));
        System.out.println(level);*/

        List<Map<String, Object>> topPost = postService.getTopPost();
        System.out.println(topPost);
    }


}
