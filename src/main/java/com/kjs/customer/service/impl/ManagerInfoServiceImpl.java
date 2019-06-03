package com.kjs.customer.service.impl;

import com.kjs.customer.dao.ManagerMapper;
import com.kjs.customer.entity.Manager;
import com.kjs.customer.exception.ForbiddenUserException;
import com.kjs.customer.pojo.ManagerInfo;
import com.kjs.customer.service.*;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;

/**
 * 后台用户管理
 */

@Service
public class ManagerInfoServiceImpl implements ManagerInfoService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ManagerMapper managerMapper;

    /**
     * 通过名称查找用户
     * @param username
     * @return
     */
    public ManagerInfo findByUsername(String username) {

        ManagerInfo managerInfo =  managerMapper.findByUsername(username);
        if (managerInfo == null) {
            throw new UnknownAccountException();
        }
        if (managerInfo.getState() == 2) {
            throw new ForbiddenUserException();
        }
        if (managerInfo.getPidsList() == null) {
            managerInfo.setPidsList(Collections.singletonList(0));
        } else if (managerInfo.getPidsList().size() == 0) {
            managerInfo.getPidsList().add(0);
        }

        return managerInfo;
    }

    public Boolean updateLoginTimeByUsername(String username){
        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setUpdatedTime(new Date());
        int count = 0;
        try {
              count = managerMapper.updateLoginTimeByUsername(manager);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if (count>0){
            return true;
        }
        else return false;
    }
}
