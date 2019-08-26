package com.dp.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author dp
 * @create 2019-08-26 21:52
 */
public interface BaseService<T> extends IService<T> {

    void join(Map<String ,Object> stringObjectMap,String field);

    void join(List<Map<String,Object>> datas,String field);

    void join(IPage<Map<String,Object>> pageDate,String field);



}
