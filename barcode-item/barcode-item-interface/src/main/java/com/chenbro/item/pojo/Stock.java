package com.chenbro.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName Stock
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/21 13:30
 * @Version 1.0
 **/
@Table(name = "tb_stock")
public class Stock {
    @Id
    private Long skuId;
    private Integer seckillStock;   // 秒杀可用库存
    private Integer seckillTotal;   // 已秒杀数量
    private Integer stock;      // 正常库存

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(Integer seckillStock) {
        this.seckillStock = seckillStock;
    }

    public Integer getSeckillTotal() {
        return seckillTotal;
    }

    public void setSeckillTotal(Integer seckillTotal) {
        this.seckillTotal = seckillTotal;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
