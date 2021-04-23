package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName BarcodeRegistryAppplication
 * @Description TODO
 * @Author z8777
 * @Date 2020/11/4 11:33
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer  //启用eureka服务端
public class BarcodeRegistryAppplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeRegistryAppplication.class, args);
    }
}
