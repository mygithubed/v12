package com.it.v12.api;

import com.it.v12.common.base.IBaseService;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TUser;
import com.sun.xml.internal.ws.api.pipe.Tube;

/**
 * @author:曾志鹏
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

    /**
     * 用户的凭证
     * @param user 登入的用户
     * @return 返回是否在redis中是否有key UUID
     */
    RsetBean checklogin(TUser user);

    /**
     * 判断用户是否登入
     * @param uuid 存入redis中的key
     * @return 返回登入的状态
     */
    RsetBean checkLoginStarts(String uuid);

    /**
     * 注销用户
     * @param uuid 用户的凭证信息
     * @return 注销结果
     */
    RsetBean loginOut(String uuid);

}
