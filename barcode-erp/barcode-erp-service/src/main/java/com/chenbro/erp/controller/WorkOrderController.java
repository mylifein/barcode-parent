package com.chenbro.erp.controller;

import com.chenbro.erp.pojo.ErpCost;
import com.chenbro.erp.pojo.ErpCostType;
import com.chenbro.erp.pojo.ErpStock;
import com.chenbro.erp.pojo.WorkOrder;
import com.chenbro.erp.service.WorkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName WorkOrderController
 * @Description TODO
 * @Author z8777
 * @Date 2021/2/6 18:37
 * @Version 1.0
 **/
@Controller
@RequestMapping("erp")
@Api("ERP服务接口")
public class WorkOrderController {


    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("wo/{wo}")
    @ApiOperation(value = "根据工单号，查询工单信息", notes = "工单查询")
    @ApiImplicitParam(name = "wo", required = true, value = "工单号")
    public ResponseEntity<WorkOrder> queryWOByWO(@PathVariable("wo") String wo) {
        WorkOrder workOrder = workOrderService.queryWOByWO(wo);
        if (ObjectUtils.isEmpty(workOrder)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(workOrder);
    }

    @GetMapping("stocks")
    public ResponseEntity<List<ErpStock>> queryErpStocks() {
        List<ErpStock> stocks = workOrderService.selectStocks();
        if (CollectionUtils.isEmpty(stocks)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stocks);
    }

    @PostMapping("costs")
    public ResponseEntity<List<ErpCost>> qeuryErpCosts(
            @RequestParam(value = "orgId", required = true, defaultValue = "572") Integer orgId,
            @RequestParam(value = "costType", required = false) String costType,
            @RequestParam(value = "segment", required = false) String segment,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        List<ErpCost> costs = workOrderService.selectErpCosts(orgId, costType, segment, file);
        if (CollectionUtils.isEmpty(costs)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(costs);
    }

    @GetMapping("costtype")
    public ResponseEntity<List<ErpCostType>> queryErpCostTypes() {
        List<ErpCostType> erpCostTypes = workOrderService.selectErpCostType();
        if (CollectionUtils.isEmpty(erpCostTypes)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(erpCostTypes);
    }

}
