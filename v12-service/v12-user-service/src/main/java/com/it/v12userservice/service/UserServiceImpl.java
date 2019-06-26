package com.it.v12userservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IUserService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TUser;
import com.it.v12.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author:曾志鹏
 * Date:2019/6/25
 * Time:12:16
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {

    @Autowired
    private TUserMapper userMapper;

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
}
