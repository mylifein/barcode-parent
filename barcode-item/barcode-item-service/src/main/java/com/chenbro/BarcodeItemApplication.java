package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName BarcodeItemApplication
 * @Description TODO
 * @Author z8777
 * @Date 2020/11/4 16:13
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chenbro.item.mapper")
public class BarcodeItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeItemApplication.class);
    }
}


