package com.dp.homework.service.impl;

import com.dp.homework.entity.Category;
import com.dp.homework.mapper.CategoryMapper;
import com.dp.homework.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public void join(Map<String, Object> map, String field) {

        Map<String, Object> joinColumns = new HashMap<>();
        //字段的值
        String linkfieldValue  = map.get(field).toString();
        Category category = this.getById(linkfieldValue);
        joinColumns.put("id",category.getId());
        joinColumns.put("name",category.getName());
        joinColumns.put("icon",category.getIcon());
        map.put("category",joinColumns);
    }
}
