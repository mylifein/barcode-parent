package com.chenbro.product.service;

import com.chenbro.erp.pojo.ErpStock;
import com.chenbro.product.bo.StockBo;
import com.chenbro.product.client.ErpClient;
import com.chenbro.product.mapper.StockMapper;
import com.chenbro.product.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName ProductService
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 11:11
 * @Version 1.0
 **/
@Service
public class ProductService {

    @Autowired
    private ErpClient erpClient;

    @Autowired
    private StockMapper stockMapper;

    /* *
     * @Description //TODO 根据库存最大的 replyId,查询最新的VMI库存数据
     * @Date 2021/3/29 8:18
     * @return
     **/
    public Product queryCurrentStocks() {
        Product product = new Product();
        product.setErpStocks(erpClient.queryErpStocks());
        product.setStocks(stockMapper.selectStocksByCurrentReplyId());
        return product;
    }


    public List<StockBo> queryDiffStocks() {
        List<ErpStock> stocks = erpClient.queryErpStocks();
        List<StockBo> stockBos = stockMapper.selectStocksByGroup();
        Map<String, Integer> matMap = new HashMap<>();

        stockBos = stockBos.stream().map(stockBo -> {
            String key = stockBo.getDelMatno();
            if (matMap.containsKey(key)) {
                matMap.put(key, matMap.get(key) + stockBo.getToatalQty());
            } else {
                matMap.put(key, stockBo.getToatalQty());
            }
            return stockBo;
        }).collect(Collectors.toList()).stream().map(stockBo -> {
            stocks.stream().anyMatch(erpStock -> {
                boolean result = erpStock.getDelMatno().equals(stockBo.getDelMatno());
                if (result) {
                    stockBo.setErpQty(erpStock.getQuantity());
                    stockBo.setSubCode(erpStock.getSubinventoryCode());
                    stockBo.setDiffQty(erpStock.getQuantity() - matMap.get(stockBo.getDelMatno()));
                }
                return result;
            });
            return stockBo;
        }).collect(Collectors.toList());
        return stockBos;
    }


}

