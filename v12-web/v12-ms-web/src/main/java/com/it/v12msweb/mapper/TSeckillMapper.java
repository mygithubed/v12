package com.it.v12msweb.mapper;


import com.it.v12msweb.entity.TSeckill;

import java.util.List;

public interface TSeckillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSeckill record);

    int insertSelective(TSeckill record);

    TSeckill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSeckill record);

    int updateByPrimaryKey(TSeckill record);

    List<TSeckill> getCanStartSeckill();

    List<TSeckill> getCanStopSeckill();
}
