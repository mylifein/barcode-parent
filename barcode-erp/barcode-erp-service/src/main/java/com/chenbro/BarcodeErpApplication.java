package com.chenbro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName BarcodeErpApplication
 * @Description TODO
 * @Author z8777
 * @Date 2021/2/6 18:06
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("com.chenbro.erp.mapper")
public class BarcodeErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeErpApplication.class, args);
    }

}
