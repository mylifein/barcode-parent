package com.chenbro.product.client;

import com.chenbro.erp.pojo.ErpStock;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ErpClientCallBack
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 21:29
 * @Version 1.0
 **/

public class ErpClientCallBack implements ErpClient {

    @Override
    public List<ErpStock> queryErpStocks() {
        return null;
    }

}
