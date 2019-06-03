package com.kjs.customer.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kjs.customer.entity.Manager;
import com.kjs.customer.pojo.ManagerInfo;

/**
 * 后台管理用户表 Mapper
 *
 * @author yellow
 * @version 1.0
 * @since 2019/01/02
 */
public interface ManagerMapper extends BaseMapper<Manager> {

    ManagerInfo findByUsername(String username);

    Integer updateLoginTimeByUsername(Manager manager);

}
