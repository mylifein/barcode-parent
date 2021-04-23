package com.chenbro.item.service;

import com.chenbro.item.mapper.CategoryMapper;
import com.chenbro.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CategoryService
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/16 11:35
 * @Version 1.0
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    
    /**
    * @Description //TODO 根据父节点 查询子节点
    * @Date 2020/12/16 13:35
    * @return
    **/
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return categoryMapper.select(record);
    }

    /**
    * @Description //TODO 根据多级id 查询分类名称
    * @Date 2020/12/21 8:53
    * @return
    **/
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}
