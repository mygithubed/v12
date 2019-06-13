package com.it.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProduct;
import com.it.v12.entity.TProductDesc;
import com.it.v12.mapper.TProductDescMapper;
import com.it.v12.mapper.TProductMapper;
import com.it.v12.pojo.TProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private TProductDescMapper productDescMapper;

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

    /**
     * 返回新增商品的ID
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public Long saves(TProductVO vo) {
        //存储商品的信息
        TProduct product = vo.getProduct();
        //默认数据没有删除
        product.setFlag(true);
        //需要主键回填
        int num = productMapper.insert(product);
        //商品的描述信息
        String productDesc = vo.getProductDesc();
        TProductDesc desc  = new TProductDesc();
        desc.setProductDesc(productDesc);
        desc.setProductId(product.getId());
        productDescMapper.insert(desc);
        return product.getId();
    }

    /**
     * 批量的删除
     * @param listId
     * @return
     */
    @Override
    public Long batchDel(List<Long> listId) {
        return productMapper.batchUpdateByFlag(listId);
    }

    /**
     * 逻辑删除及重写方法
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        //更新语句
        TProduct product = new TProduct();
        //根据id
        product.setId(id);
        //逻辑的删除
        product.setFlag(false);
        return  productMapper.updateByPrimaryKeySelective(product);
    }
}
