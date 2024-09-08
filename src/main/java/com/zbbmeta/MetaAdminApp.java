package com.zbbmeta;


import com.zbbmeta.config.EnableFormValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFormValidator
@SpringBootApplication
public class MetaAdminApp {

    public static void main(String[] args) {
        SpringApplication.run(MetaAdminApp.class,args);
    }
}
