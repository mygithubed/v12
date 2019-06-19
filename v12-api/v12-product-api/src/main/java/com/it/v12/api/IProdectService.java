package com.it.v12.api;

import com.github.pagehelper.PageInfo;
import com.it.v12.common.base.IBaseService;
import com.it.v12.entity.TProduct;
import com.it.v12.pojo.TProductVO;

import java.util.List;

/**
 * @author:曾志鹏
 * Date:2019/6/11
 * Time:17:11
 */
public interface IProdectService extends IBaseService<TProduct>{

    /**
     * 分页的方法
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<TProduct> page(Integer pageIndex, Integer pageSize);

    /**
     * 返回新增商品的ID
     * @param vo
     * @return
     */
    Long saves(TProductVO vo);

    /**
     * 批量的删除
     * @param listId
     * @return
     */
    Long batchDel(List<Long> listId);

    /**
     * 根据ID来获取商品的信息
     * @param id
     * @return
     */
    TProductVO getTProductVO(Long id);

    /**
     * 根据id来修改商品的信息和商品描述的信息
     * @param vo
     */
    void updateById(TProductVO vo);
}
