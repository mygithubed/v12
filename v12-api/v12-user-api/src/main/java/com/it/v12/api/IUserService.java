package com.it.v12.api;

import com.it.v12.common.base.IBaseService;
import com.it.v12.entity.TUser;

/**
 * Author:曾志鹏
 * Date:2019/6/25
 * Time:11:43
 */
public interface IUserService extends IBaseService<TUser>{
    /**
     * 根据ID来激活用户
     * @param id
     * @return
     */
    void updateStart(Long id);
}
