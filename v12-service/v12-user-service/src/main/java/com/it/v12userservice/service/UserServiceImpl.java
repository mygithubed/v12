package com.it.v12userservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IUserService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.common.constant.CookieConstant;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TUser;
import com.it.v12.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author:曾志鹏
 * Date:2019/6/25
 * Time:12:16
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {

    @Autowired
    private TUserMapper userMapper;

    @Resource(name = "stringReadis")
    private RedisTemplate redisTemplate;

    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }


    @Override
    public int insertSelective(TUser record) {
        super.insertSelective(record);
        //返回主键的信息
        return  record.getId().intValue();
    }

    @Override
    public void updateStart(Long id) {
         userMapper.updateUserStart(id);
    }

    @Override
    public RsetBean checklogin(TUser user) {
        //数据库中查询用否是否存在
        TUser creatUser =  userMapper.selectByUserName(user.getUsername());
        if(creatUser!=null){
            if(creatUser.getPassword().equals(user.getPassword())){
                //将凭证存在redis中去
                //创建凭证
                String uuid = UUID.randomUUID().toString();
                String key = new StringBuilder(CookieConstant.USER_TOKEN).append(uuid).toString();
                //将密码的值去除
                user.setPassword("");
                redisTemplate.opsForValue().set(key,user);
                //设置有效时间
                redisTemplate.expire(key,30, TimeUnit.MINUTES);
              return  new RsetBean("200",uuid);
            }
        }
        return new RsetBean("404","登入失败！");
    }

    @Override
    public RsetBean checkLoginStarts(String uuid) {
        //拼接存入的key值
        String key = new StringBuilder(CookieConstant.USER_TOKEN).append(uuid).toString();
        //在redis中查询该值是否存在
        TUser loginUser = (TUser)redisTemplate.opsForValue().get(key);
        if(loginUser!=null){
            //需要刷新有效期  还是置为30分钟
            redisTemplate.expire(key,30,TimeUnit.MINUTES);
            return new RsetBean("200",loginUser);
        }
        return new RsetBean("404",null);
    }

    @Override
    public RsetBean loginOut(String uuid) {
        //拼接存入的key值
        String key = new StringBuilder(CookieConstant.USER_TOKEN).append(uuid).toString();
        //删除在redis中的key
        Boolean delete = redisTemplate.delete(key);
        if(delete){
            return  new RsetBean("200","删除成功!");
        }
        return new RsetBean("404","删除失败");
    }
}

