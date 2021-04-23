package com.chenbro.item.service;

import com.chenbro.item.mapper.SpecGroupMapper;
import com.chenbro.item.mapper.SpecParamMapper;
import com.chenbro.item.pojo.SpecGroup;
import com.chenbro.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SpecificationService
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/18 15:30
 * @Version 1.0
 **/
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * @return
     * @Description //TODO 根据分类id 查询分数组
     * @Date 2020/12/18 15:42
     **/
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return groupMapper.select(record);
    }

    /**
     * @return
     * @Description //TODO 根据组 条件 查询规格参数
     * @Date 2020/12/18 16:56
     **/
    public List<SpecParam> queryParamsByGid(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return paramMapper.select(record);
    }

    /**
    * @Description //TODO 根据 id 更新组信息
     * @Date 2020/12/19 23:21
    * @return
    **/
    public void updateSpecGroup(SpecGroup specGroup) {
        groupMapper.updateByPrimaryKeySelective(specGroup);
    }

    /**
    * @Description //TODO 添加 组
    * @Date 2020/12/19 23:31
    * @return
    **/
    public void addSpecGroup(SpecGroup specGroup) {
        groupMapper.insertSelective(specGroup);
    }

    /**
     * @Description //TODO 根据Id 删除组信息
     * @Date 2020/12/20 9:37
     * @return
     **/
    public void deleteGroupByGid(Long gid) {
        groupMapper.deleteByPrimaryKey(gid);
    }
}
