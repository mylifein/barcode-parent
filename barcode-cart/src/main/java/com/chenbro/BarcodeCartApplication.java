package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName BarcodeCartApplication
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/7 8:35
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BarcodeCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeCartApplication.class);
    }

}
