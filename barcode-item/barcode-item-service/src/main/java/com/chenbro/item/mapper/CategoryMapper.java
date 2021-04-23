package com.chenbro.item.mapper;

import com.chenbro.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName CategoryMapper
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/16 11:31
 * @Version 1.0
 **/
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {

}
