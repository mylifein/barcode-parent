package com.chenbro.product.client;

import com.chenbro.erp.api.StocksApi;
import com.chenbro.erp.pojo.ErpStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName ErpClient
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 9:03
 * @Version 1.0
 **/
@Component
@FeignClient(value = "erp-service")
public interface ErpClient extends StocksApi {

}
