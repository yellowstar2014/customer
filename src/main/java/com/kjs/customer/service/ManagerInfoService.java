package com.kjs.customer.service;

import com.kjs.customer.pojo.ManagerInfo;

/**
 * @author yellow
 * @date 2019/5/27 17:01
 * 温馨提醒:
 * 代码千万行，
 * 注释第一行。
 * 命名不规范，
 * 同事两行泪。
 */
 public interface ManagerInfoService {

    /**
     * 通过名称查找用户
     * @param username
     * @return
     */
     ManagerInfo findByUsername(String username);

    /**
     * 更新用户的登录时间
     * @param username
     * @return
     */
    Boolean updateLoginTimeByUsername(String username);
}
