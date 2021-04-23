package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName BarcodeAuthApplication
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 13:44
 * @Version 1.0
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class BarcodeAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeAuthApplication.class);
    }
}
