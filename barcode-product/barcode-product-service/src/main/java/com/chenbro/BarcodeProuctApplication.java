package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName BarcodeProuctApplication
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 8:12
 * @Version 1.0
 **/

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients         // 启用feign组件
@MapperScan("com.chenbro.product.mapper")
public class BarcodeProuctApplication {


    public static void main(String[] args) {
        SpringApplication.run(BarcodeProuctApplication.class);
    }
}
