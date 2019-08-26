package com.dp.homework.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dp.homework.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author dp
 * @create 2019-08-26 21:56
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {


    @Override
    public void join(Map<String, Object> stringObjectMap, String field) {

    }

    @Override
    public void join(List<Map<String, Object>> datas, String field) {
                datas.forEach(map->{
                    join(map,field);
                });
    }

    @Override
    public void join(IPage<Map<String, Object>> pageDate, String field) {

        List<Map<String, Object>> records = pageDate.getRecords();
        this.join(records,field);
    }
}
