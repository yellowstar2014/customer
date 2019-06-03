package com.kjs.customer.controller;

import com.kjs.customer.pojo.ManagerInfo;
import com.kjs.customer.pojo.requestPojo.LoginParam;
import com.kjs.customer.pojo.responsePojo.BaseResponse;
import com.kjs.customer.redis.RedisService;
import com.kjs.customer.service.impl.ManagerInfoServiceImpl;
import com.kjs.customer.shiro.ShiroKit;
import com.kjs.customer.util.JWTUtil;
import com.kjs.customer.version.ApiVersion;
import io.swagger.annotations.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录接口类
 */
@Api(value = "登录请求接口类", tags = "登录", description = "用户请求登录获取Token")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机无权限访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)

@RestController
@ApiVersion(1)
@RequestMapping("{version}/api")
public class LoginController {

    @Autowired
    private RedisService redisService;

    @Resource
    private ManagerInfoServiceImpl managerInfoService;

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "登录认证接口", notes = "不管是接口还是WebSocket连接都需要先调用此接口拿到Token，然后再通过Token调用相应接口", produces = "application/json")
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestHeader(name="Content-Type", defaultValue = "application/json") String contentType,
                                      @ApiParam(value = "登录参数") @RequestBody LoginParam loginParam) {
        _logger.info("用户请求登录获取Token");
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        ManagerInfo user = managerInfoService.findByUsername(username);
        //随机数盐
        String salt = user.getSalt();
        //原密码加密（通过username + salt作为盐）
        String encodedPassword = ShiroKit.md5(password, username + salt);
        _logger.info("#################encodedPassword:"+encodedPassword);
        if (user.getPassword().equals(encodedPassword)) {

            boolean flag = managerInfoService.updateLoginTimeByUsername(username);
             //用JWT框架生成
             String token = token = JWTUtil.sign(username, encodedPassword);
                if (redisService.exists("token:"+username)){//如果缓存中存在token，则先删除
                    redisService.remove("token:"+username);
                    _logger.info("从缓存数据库redis中已删除旧Token");
                }
                //把token放入Redis缓存
                boolean result = redisService.setAndTime("token:"+username,token,600L);
                if(!result){
                    _logger.info("Token写入redis缓存数据库失败");
                }
                else {
                    _logger.info("JWT生成的新Token写入redis缓存数据库成功："+token);
                }

            return new BaseResponse<>(true, "Login success", token);
        } else {
            throw new UnauthorizedException();
        }
    }


    /**
     * 测试程序是否成功启动接口
     *
     * @return 是否入网
     */
    @ApiOperation(value = "测试程序是否成功启动接口", notes = "测试程序是否成功启动接口")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public BaseResponse test() {
        BaseResponse result = new BaseResponse();
        result.setSuccess(true);
        result.setMsg("程序已成功启动v1");
        return result;
    }

    /**
     * 测试程序是否成功启动接口
     *
     * @return 是否入网
     */
    @ApiOperation(value = "测试消息队列远程调用此接口", notes = "测试消息队列远程调用此接口")
    @RequestMapping(value = "/testMQ", method = RequestMethod.GET)
    public BaseResponse testMQ(String username) {
        BaseResponse result = new BaseResponse();

        boolean flag = managerInfoService.updateLoginTimeByUsername(username);
        if (flag == true){
            result.setSuccess(true);
            result.setMsg("消息队列远程调用更新用户登录时间接口成功");
        }
        else {
            result.setSuccess(false);
            result.setMsg("消息队列远程调用更新用户登录时间接口失败");
        }

        return result;
    }



}
