package com.it.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProduct;
import com.it.v12.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Author:曾志鹏
 * Date:2019/6/11
 * Time:17:15
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProdectService {

    //注入具体的

    @Autowired
    private TProductMapper productMapper;

    /**
     *
     * @return
     */
    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }


    /**
     *分页的设置
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        //1.设置分页参数
        PageHelper.startPage(pageIndex,pageSize);
        //2.获取数据
        List<TProduct> list = list();
        //3.构建一个分页对象,第二个参数是控制一次性显示几个下标页码数
        PageInfo<TProduct> pageInfo = new PageInfo<TProduct>(list,2);
        //返回分页后的数据
        return pageInfo;
    }
}
