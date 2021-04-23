package com.chenbro.product.test;

import com.chenbro.product.bo.StockBo;
import com.chenbro.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName TProductService
 * @Description TODO
 * @Author z8777
 * @Date 2021/4/1 10:58
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TProductService {

    @Autowired
    private ProductService productService;

    @Test
    public void testQueryStocks() {
        List<StockBo> stockBos = productService.queryDiffStocks();
        stockBos.stream().forEach(stockBo -> System.out.println("delMatno:" + stockBo.getDelMatno() + " totalQty:" + stockBo.getToatalQty() + " erpQty:" + stockBo.getErpQty() + " diffQty:" +stockBo.getDiffQty()));
    }
}


