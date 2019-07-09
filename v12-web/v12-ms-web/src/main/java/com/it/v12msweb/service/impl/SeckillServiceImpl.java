package com.it.v12msweb.service.impl;

import com.it.v12msweb.entity.TSeckill;
import com.it.v12msweb.exception.SeckillException;
import com.it.v12msweb.mapper.TSeckillMapper;
import com.it.v12msweb.service.ISeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author :曾志鹏
 * Date:2019/7/9
 * Time:11:18
 */
@Service
public class SeckillServiceImpl implements ISeckillService {

    @Autowired
    private ISeckillService seckillService;

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;

    @Autowired
    private TSeckillMapper tSeckillMapper;

    @Override
    public void sale(Long seckillId, Long useId) {
        //查看活动的状态
/*
        TSeckill tSeckill = tSeckillMapper.selectByPrimaryKey(Integer.parseInt(seckillId.toString()));
        String tow = "2";
        if(tow.equals(tSeckill.getStatus())){
            throw new SecurityException("不好意思！！活动已经结束了！");
        }
        String zone  = "0";
        if(zone.equals(tSeckill.getStatus())){
            throw  new SeckillException("活动还没有开始呢！亲！！");
        }
*/

        /**查询的是redis中的数据**/
        String seckillKey = new StringBuffer("seckill:").append(seckillId).toString();
        TSeckill  tSeckill = (TSeckill)redisTemplate.opsForValue().get(seckillKey);
        String tow = "2";
        if(tow.equals(tSeckill.getStatus())){
            throw new SecurityException("不好意思！！活动已经结束了！");
        }
        String zone  = "0";
        if(zone.equals(tSeckill.getStatus())){
            throw  new SeckillException("活动还没有开始呢！亲！！");
        }

        String key = new StringBuilder("seckill:product:").append(tSeckill.getId()).toString();

        Long productId =(Long)redisTemplate.opsForList().leftPop(key);

        if(productId ==  null){
            throw  new SeckillException("商品已经抢购完了！！");
        }
        //保存抢购成功的用户信息
        String lockMankey = new StringBuilder("seckill:user:").append(tSeckill.getId()).toString();

        //判断该用户是否参与了抢购
        Boolean member = redisTemplate.opsForSet().isMember(lockMankey,useId);
        if(member){
            //将数据丢回队列
            redisTemplate.opsForList().leftPush(key,productId);
            throw new SeckillException("你已经抢购成功了");
        }

        redisTemplate.opsForSet().add(lockMankey,useId);

        System.out.println("用户请购成功！！");

    }
}
