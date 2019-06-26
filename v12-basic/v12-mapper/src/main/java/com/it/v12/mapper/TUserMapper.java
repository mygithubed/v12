package com.it.v12.mapper;

import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TUser;

/**
 *
 * @author Administrator
 */
public interface TUserMapper extends IBaseDao<TUser>{

    /**
     * 根据用户的ID来修改用户的状态
     * @param id
     * @return
     */
    void updateUserStart(Long id);
}
