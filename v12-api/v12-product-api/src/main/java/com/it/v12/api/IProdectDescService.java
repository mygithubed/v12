package com.it.v12.api;

import com.it.v12.common.base.IBaseService;
import com.it.v12.entity.TProductDesc;

/**
 * Date:2019/6/19
 * Time:11:26
 * @author 曾志鹏
 */
public interface IProdectDescService extends IBaseService<TProductDesc> {

    /**
     * 根据商品的ID返回商品的描述
     * @param id
     * @return
     */
    String selectByproductDesc(Long id);
}
