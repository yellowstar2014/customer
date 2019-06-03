package com.kjs.customer.pojo.requestPojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 登录认证接口参数
 *
 * @author yellow
 * @version 1.0
 * @since 2019/1/9
 */
@ApiModel(value = "登录认证接口参数", description = "登录认证接口参数")
public class LoginParam {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "admin", required = true)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
