package com.it.v12.mapper;

import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TUser;

/**
 *
 * @author 曾志鹏
 */
public interface TUserMapper extends IBaseDao<TUser>{

    /**
     * 根据用户的ID来修改用户的状态
     * @param id 修改的用户ID
     */
    void updateUserStart(Long id);

    /**
     *  判断用户是否存在
     * @param username 登入时输入的用户名
     * @return  用户是否存在
     */
    TUser selectByUserName(String username);
}
