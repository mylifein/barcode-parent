package com.chenbro.item.controller;

import com.chenbro.common.pojo.PageResult;
import com.chenbro.item.bo.SpuBo;
import com.chenbro.item.pojo.Sku;
import com.chenbro.item.pojo.Spu;
import com.chenbro.item.pojo.SpuDetail;
import com.chenbro.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/20 17:22
 * @Version 1.0
 **/
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * @return
     * @Description //TODO 根据条件分页查询spu
     * @Date 2020/12/20 19:42
     **/
    @GetMapping("spu/page")     // key=&saleable=true&page=1&rows=5
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "saleable", required = false) Boolean saleable,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "5") Integer rows
    ) {
        PageResult<SpuBo> result = goodsService.querySpuByPage(key, saleable, page, rows);
        if (ObjectUtils.isEmpty(result) || CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /* *
     * @Description //TODO 根据spuId 查询spuDetail
     * @Date 2020/12/21 15:26
     * @return
     **/
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId") Long spuId) {
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    /* *
     * @Description //TODO 根据spuId 查询 sku 集合
     * @Date 2020/12/21 16:37
     * @return
     **/
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id") Long spuId) {
        List<Sku> skus = goodsService.querySkusBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }


    /* *
     * @Description //TODO 保存商品信息
     * @Date 2020/12/21 14:08
     * @return
     **/
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo) {
        goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* *
     * @Description //TODO 更新商品信息
     * @Date 2020/12/22 9:41
     * @return
     **/
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo) {
        goodsService.updateGoods(spuBo);
        return ResponseEntity.noContent().build();
    }


}