package com.it.v12cartservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.ICartService;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.pojo.CartItem;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * @author :曾志鹏
 * Date:2019/6/29
 * Time:11:35
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource(name="myRedisTemlate")
    private RedisTemplate redisTemplate;

    @Override
    public RsetBean add(String uuid, Long productId, Integer count) {
        //构建一个key
        String key=  new StringBuilder("user:cart:").append(uuid).toString();
        //是否存在该购物车
        CartItem cartItem =(CartItem) redisTemplate.opsForHash().get(key, productId.toString());
        if(cartItem!=null){
            //更改商品的数量即可
            cartItem.setCount(cartItem.getCount()+count);
            //更改时间
            cartItem.setUpdateDate(new Date());
        }else {
            //创建一个购物车的对象
            cartItem = new CartItem(productId,count,new Date());
        }
        //存入redis中
        redisTemplate.opsForHash().put(key,productId.toString(),cartItem);
        //有效期的保存
        redisTemplate.expire(key,7,TimeUnit.DAYS);

        return new RsetBean("200","添加购物车成功！");
    }

    /**
     *  删除商品
     * @param uuid ID
     * @param productId 商品的ID
     * @return
     */
    @Override
    public RsetBean remove(String uuid, Long productId) {
        //构建一个key
        String key=  new StringBuilder("user:cart:").append(uuid).toString();
        Long deleteLong = redisTemplate.opsForHash().delete(key, productId.toString());
        if(deleteLong>0){
            //刷新有效期
            redisTemplate.expire(key,7,TimeUnit.DAYS);
            return  new RsetBean("200","删除成功！");
        }
        return new RsetBean("404","删除失败！");
    }

    @Override
    public RsetBean update(String uuid, Long productId, Integer count) {
        //构建一个key
        String key=  new StringBuilder("user:cart:").append(uuid).toString();
        //查询有没有
        CartItem item =(CartItem) redisTemplate.opsForHash().get(key, productId.toString());
        if(item != null){
            //更改数量
            item.setCount(count);
            //更新时间
            item.setUpdateDate(new Date());
            //保存
            redisTemplate.opsForHash().put(key,productId.toString(),item);
            //刷新有效期
            redisTemplate.expire(key,7,TimeUnit.DAYS);

            return  new RsetBean("200","购物车商品更新成功！");
        }
        return new RsetBean("404","购物车更新失败！");
    }

    /***
     *  查询
     * @param uuid 客户端保存的ID
     * @return
     */
    @Override
    public RsetBean query(String uuid) {
        //构建一个key
        String key=  new StringBuilder("user:cart:").append(uuid).toString();
        //获取购物车中的所有内容
        Map<Object,Object> cartMap = redisTemplate.opsForHash().entries(key);
        //需要排序？？
        TreeSet<CartItem> cartItems= new TreeSet<>();
        Set<Map.Entry<Object, Object>> carts = cartMap.entrySet();
        for (Map.Entry<Object, Object> cart : carts) {
            CartItem item = (CartItem)cart.getValue();
            cartItems.add(item);
        }
        //刷新有效期
        redisTemplate.expire(key,7,TimeUnit.DAYS);
        return new RsetBean("200",cartItems);
    }
}
