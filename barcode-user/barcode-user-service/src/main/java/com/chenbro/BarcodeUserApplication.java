package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName BarcodeUserApplication
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/24 10:29
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chenbro.user.mapper")
public class BarcodeUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeUserApplication.class);
    }
}
