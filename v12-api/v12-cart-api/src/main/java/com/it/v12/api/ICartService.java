package com.it.v12.api;

import com.it.v12.common.pojo.RsetBean;

/**
 * @author :曾志鹏
 * Date:2019/6/28
 * Time:23:25
 */
public interface ICartService {

    /**
     * 添加商品到购物车
     * @param uuid 购物车的ID
     * @param productId 商品的ID
     * @param count 商品的数量
     * @return 添加的状态
     */
   RsetBean  add(String uuid,Long productId,Integer count);

    /**
     * 删除商品
     * @param uuid ID
     * @param productId 商品的ID
     * @return 返回状态
     */
   RsetBean remove(String uuid,Long productId);

    /**
     * 修改商品的数量
     * @param uuid ID
     * @param productId 商品的ID
     * @param count 商品的数量
     * @return 返回
     */
   RsetBean update(String uuid,Long productId,Integer count);
    /**
     *  查询商品信息
     * @param uuid 客户端保存的ID
     * @return 返回查询的商品信息
     */
   RsetBean query(String uuid);
}
