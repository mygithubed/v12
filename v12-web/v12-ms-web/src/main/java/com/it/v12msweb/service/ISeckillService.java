package com.it.v12msweb.service;

/**
 * @author :曾志鹏
 * Date:2019/7/9
 * Time:11:17
 */
public interface ISeckillService {
    /**
     * 秒杀的实现
     * @param seckillId 秒杀商品的ID
     * @param useId 对应用户的ID
     */
    void sale(Long seckillId,Long useId);
}
