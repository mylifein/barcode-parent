package com.chenbro.product.pojo;

import com.chenbro.erp.pojo.ErpStock;

import java.util.List;

/**
 * @ClassName Product
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/29 8:09
 * @Version 1.0
 **/
public class Product {

    private List<Stock> stocks;

    private List<ErpStock> erpStocks;

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<ErpStock> getErpStocks() {
        return erpStocks;
    }

    public void setErpStocks(List<ErpStock> erpStocks) {
        this.erpStocks = erpStocks;
    }
}
