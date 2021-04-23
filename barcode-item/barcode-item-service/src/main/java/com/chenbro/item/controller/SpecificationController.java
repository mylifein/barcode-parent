package com.chenbro.item.controller;

import com.chenbro.item.pojo.SpecGroup;
import com.chenbro.item.pojo.SpecParam;
import com.chenbro.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SpecificationController
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/18 15:32
 * @Version 1.0
 **/
@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * @return
     * @Description //TODO 根据分类id 查询分数组
     * @Date 2020/12/18 15:40
     **/
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> groups = specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * @return
     * @Description //TODO 根据 id 更新组对象信息
     * @Date 2020/12/19 23:18
     **/
    @PutMapping("group")
    public ResponseEntity<Void> updateGroup(@RequestBody SpecGroup specGroup) {
        specificationService.updateSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @return
     * @Description //TODO 根据Id 删除组信息
     * @Date 2020/12/20 9:37
     **/
    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> deleteGroupByGid(@PathVariable("gid") Long gid) {

        specificationService.deleteGroupByGid(gid);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @return
     * @Description //TODO 添加组
     * @Date 2020/12/19 23:29
     **/
    @PostMapping("group")
    public ResponseEntity<Void> addGroup(@RequestBody SpecGroup specGroup) {
        specificationService.addSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @return
     * @Description //TODO 根据组 条件 查询规格参数
     * @Date 2020/12/18 16:55
     **/
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    ) {
        List<SpecParam> params = specificationService.queryParamsByGid(gid, cid, generic, searching);
        if (CollectionUtils.isEmpty(params)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }


}
