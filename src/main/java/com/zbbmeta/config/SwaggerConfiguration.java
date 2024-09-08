package com.zbbmeta.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@Slf4j
@Configuration
@EnableSwagger2
@AllArgsConstructor
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket buildDocket() {
        HashSet<String> strings = new HashSet<>();
        strings.add("application/json");
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                //设置返回数据类型
                .produces(strings)
                //分组名称
                .groupName("学习zbbmeta用户管理文档")
                .select()
                //这里指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zbbmeta.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("学习zbbmeta用户管理","","");
        return new ApiInfoBuilder()
                .title("学习zbbmeta用户管理文档")
                .description("学习zbbmeta用户管理")
                .contact(contact)
                .version("1.0.0").build();
    }
}
