package com.tech.repair;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("便捷报修 应用接口在线测试系统")
                        .description("闫润宁加油")
                        .contact(new Contact("王镭树", null, "1158545448@qq.com"))
                        .version("闫润宁加油！")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tech.repair.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
