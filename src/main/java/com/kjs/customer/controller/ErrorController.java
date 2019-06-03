package com.kjs.customer.controller;

import com.kjs.customer.pojo.responsePojo.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口类
 */
@Api(value = "请求错误接口类", tags = "错误", description = "用户请求发生错误")
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机无权限访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@RestController
@RequestMapping(value = "/error")
public class ErrorController {

    private static final Logger _logger = LoggerFactory.getLogger(ErrorController.class);


    /**
     * 测试程序是否成功启动接口
     *
     * @return 是否入网
     */
    @ApiOperation(value = "401", notes = "未授权客户机无权限访问数据")
    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public BaseResponse error() {
        BaseResponse result = new BaseResponse();
        result.setSuccess(false);
        result.setMsg("未授权客户机无权限访问数据");
        return result;
    }

}
