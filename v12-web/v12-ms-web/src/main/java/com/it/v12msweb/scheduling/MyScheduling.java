package com.it.v12msweb.scheduling;

import com.it.v12msweb.entity.TSeckill;
import com.it.v12msweb.mapper.TSeckillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author :曾志鹏
 * Date:2019/7/8
 * Time:20:54
 */
@Component
public class MyScheduling {

    @Autowired
    private TSeckillMapper tSeckillMapper;

    @Autowired
    private RedisTemplate<String ,Object>  redisTemplate;

    /**定时扫描开启秒杀活动*/
    @Scheduled(cron = "0/10 * * * * *")
    public void startSeckill(){
        //查询数据表
         List<TSeckill> listSeckill = tSeckillMapper.getCanStartSeckill();
        //获取符合的记录，已到点没有开启的的活动
        if(listSeckill!=null && !listSeckill.isEmpty()){
            //修改这些记录的状态，为已开启
            for (TSeckill seckill : listSeckill) {
                seckill.setStatus("1");
                //更新
                tSeckillMapper.updateByPrimaryKeySelective(seckill);

                //创建一个待秒杀的队列
                String key = new StringBuilder("seckill:product:").append(seckill.getId()).toString();
                //确定队列的长度
                Integer count = seckill.getCount();
                for (Integer i =0 ;i<count;i++){
                    redisTemplate.opsForList().leftPush(key,seckill.getProductId());
                }

                //将秒杀活动的信息存入redis中,减少直接对数据库的操作
                String seckillKey = new StringBuffer("seckill:").append(seckill.getId()).toString();
                redisTemplate.opsForValue().set(seckillKey,seckill);
            }
        }

    }

    /**定时扫描结束秒杀活动**/
    @Scheduled(cron = "0/10 * * * * *")
    public void endSeckill(){

        //查询数据表
        List<TSeckill> list = tSeckillMapper.getCanStopSeckill();
        //获取符合的记录 以到点没有结束的活动
        if(list != null && !list.isEmpty()){
            for (TSeckill seckill : list) {
                //修改这些记录的状态，为已停止
                seckill.setStatus("2");
                //更新
                tSeckillMapper.updateByPrimaryKeySelective(seckill);

                //清除redis中的队列值
                String key = new StringBuilder("seckill:product:").append(seckill.getId()).toString();
                redisTemplate.delete(key);
            }
        }

    }

}
