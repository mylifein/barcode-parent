package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName BarcodeGatewayApplication
 * @Description TODO
 * @Author z8777
 * @Date 2020/11/4 14:54
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class BarcodeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeGatewayApplication.class,args);
    }
}
