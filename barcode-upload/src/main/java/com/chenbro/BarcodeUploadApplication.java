package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName BarcodeUploadApplication
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/17 19:59
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class BarcodeUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeUploadApplication.class);
    }
}
