package com.it.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IProdectTypeService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProductType;
import com.it.v12.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Author:曾志鹏
 * Date:2019/6/15
 * Time:10:48
 * @author
 */
@Service
public class ProdectTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProdectTypeService {

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }


    @Override
    public List<TProductType> list(){
        //TODO 从Redis的缓存中获取数据

        String key ="product:type";
        //先查询一下Redis的有没有缓存
        List<TProductType> redidList =(List<TProductType>)redisTemplate.opsForValue().get(key);

        if(redidList == null){
            //从数据库中查询数据
            redidList = productTypeMapper.list();
            //将查询出来的数据存入缓存中
            redisTemplate.opsForValue().set(key,redidList);
            System.out.println("============================从数据库中查询数据================================================");
        }else{
            //从Redis的缓存中获取数据
            redidList =(List<TProductType>)redisTemplate.opsForValue().get(key);

            System.out.println("-------------从缓存中获取数据------------------------------------------------------------------------");
        }
        return redidList;
    }
}
