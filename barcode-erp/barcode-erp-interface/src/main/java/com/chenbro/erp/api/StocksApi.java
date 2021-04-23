package com.chenbro.erp.api;

import com.chenbro.erp.pojo.ErpStock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName StocksApi
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/18 9:28
 * @Version 1.0
 **/
public interface StocksApi {


    @GetMapping("erp/stocks")
    public List<ErpStock> queryErpStocks();
}
