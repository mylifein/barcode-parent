package com.chenbro.item.service;

import com.chenbro.common.pojo.PageResult;
import com.chenbro.item.mapper.BrandMapper;
import com.chenbro.item.pojo.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @ClassName BrandService
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/17 13:07
 * @Version 1.0
 **/
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * @return
     * @Description //TODO 根据查询条件分页并排序查询品牌信息
     * @Date 2020/12/17 13:24
     **/
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 初始化Example 对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据 name 模糊查询， 或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = brandMapper.selectByExample(example);

        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());

    }

    /**
     * @return void
     * @Description //TODO 新增品牌
     * @Date 2020/12/17 16:42
     **/
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {

        // 新增Brand
        brandMapper.insertSelective(brand);

        // 新增中間表
        cids.forEach(cid -> {
            brandMapper.insertCategoryAndBrand(cid, brand.getId());
        });

    }

    /* *
     * @Description //TODO 根据第三级分类 查询品牌
     * @Date 2020/12/21 10:48
     * @return
     **/
    public List<Brand> queryBrandsByCid(Long cid) {
        return brandMapper.selectBrandsByCid(cid);
    }



}
