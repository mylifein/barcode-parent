package com.chenbro.product.controller;

import com.chenbro.product.bo.StockBo;
import com.chenbro.product.mapper.StockMapper;
import com.chenbro.product.pojo.Product;
import com.chenbro.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 9:05
 * @Version 1.0
 **/
@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("stocks")
    public ResponseEntity<Product> queryCurrentStocks() {
        Product product = productService.queryCurrentStocks();
        if (ObjectUtils.isEmpty(product)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("diffstocks")
    public ResponseEntity<List<StockBo>> queryDiffStocks() {
        List<StockBo> stockBos = productService.queryDiffStocks();
        if (CollectionUtils.isEmpty(stockBos)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stockBos);
    }

}
