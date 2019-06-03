package com.kjs.customer.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的Java配置类
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.kjs.customer.controller")//扫描swagger2配置文件包名
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                // 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("com.kjs.customer.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("系统API服务")
                // 文档描述
                .description("本项目使用到的技术框架：springboot+mybatis+shiro+jwt+swagger+redis+api版本控制")
                // .termsOfServiceUrl("https://github.com/yidao620c")
                .version("v2")
                // .license("MIT 协议")
                // .licenseUrl("http://www.opensource.org/licenses/MIT")
                // .contact(new Contact("熊能","https://github.com/yidao620c","yidao620@gmail.com"))
                .build();
    }
}
