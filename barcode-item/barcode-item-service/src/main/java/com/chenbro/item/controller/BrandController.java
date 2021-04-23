package com.chenbro.item.controller;

import com.chenbro.common.pojo.PageResult;
import com.chenbro.item.pojo.Brand;
import com.chenbro.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BrandController
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/17 13:08
 * @Version 1.0
 **/
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @return
     * @Description //TODO 根据查询条件分页并排序查询品牌信息
     * @Date 2020/12/17 13:15
     **/
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        PageResult<Brand> result = brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * @return
     * @Description //TODO 新增品牌
     * @Date 2020/12/17 16:42
     **/
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @return
     * @Description //TODO 根据 第三级分类 查询品牌
     * @Date 2020/12/21 10:43
     **/
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable("cid") Long cid) {
        List<Brand> brands = brandService.queryBrandsByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }
}
