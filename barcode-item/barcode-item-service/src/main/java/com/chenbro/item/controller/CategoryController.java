package com.chenbro.item.controller;

import com.chenbro.item.pojo.Category;
import com.chenbro.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/16 11:36
 * @Version 1.0
 **/
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
    * @Description //TODO 根据父节点Id查询子节点
    * @Date 2020/12/16 13:36
    * @return
    **/
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == null || pid < 0) {
            // 400： 参数不合法
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoriesByPid(pid);
        if (CollectionUtils.isEmpty(categories)) {
            // 404： 资源服务器未找到
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
        // 200: 查询成功
        return ResponseEntity.ok(categories);
    }
}
