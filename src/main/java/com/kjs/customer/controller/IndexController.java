package com.kjs.customer.controller;

import com.kjs.customer.pojo.responsePojo.BaseResponse;
import com.kjs.customer.version.ApiVersion;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
@ApiVersion(2)
@RequestMapping("/{version}/api")
public class IndexController {


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
        result.setMsg("程序已成功启动V2");
        return result;
    }

}
