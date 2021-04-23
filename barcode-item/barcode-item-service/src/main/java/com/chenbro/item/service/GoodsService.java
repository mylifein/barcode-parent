package com.chenbro.item.service;

import com.chenbro.common.pojo.PageResult;
import com.chenbro.item.bo.SpuBo;
import com.chenbro.item.mapper.*;
import com.chenbro.item.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/20 17:20
 * @Version 1.0
 **/
@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @return
     * @Description //TODO 根据条件分页查询spu
     * @Date 2020/12/20 19:42
     **/
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {

        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加查询条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }

        // 添加上下架的过滤条件
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        // 添加分页
        PageHelper.startPage(page, rows);

        // 执行查询, 获取spu 集合
        List<Spu> spus = spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);

        // spu 集合转化成 spubo 集合
        List<SpuBo> spuBos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);

            // 查询品牌名称
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());
            // 查询分类名称
            List<String> names = categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "-"));
            return spuBo;
        }).collect(Collectors.toList());

        // 返回 pageResult<spuBo>
        return new PageResult<SpuBo>(pageInfo.getTotal(), spuBos);
    }

    /* *
     * @Description //TODO 保存商品信息
     * @Date 2020/12/21 14:10
     * @return
     **/
    @Transactional
    public void saveGoods(SpuBo spuBo) {
        // 新增 spu
        // 防止id注入, 并设置默一些字段默认值
        spuBo.setId(null);
        // 默认上架
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        spuMapper.insertSelective(spuBo);
        // 新增 spu_detail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insertSelective(spuDetail);
        saveSkuAndStock(spuBo);

        sendMsg("insert", spuBo.getId());
    }

    private void sendMsg(String type, Long id) {
        try {
            amqpTemplate.convertAndSend("item." + type, id);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    /* *
     * @Description //TODO 根据spuId 查询spuDetail
     * @Date 2020/12/21 16:24
     * @return
     **/
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        return spuDetailMapper.selectByPrimaryKey(spuId);
    }

    /* *
     * @Description //TODO 根据 spuId 查询 sku 集合
     * @Date 2020/12/21 16:38
     * @return
     **/
    public List<Sku> querySkusBySpuId(Long spuId) {
        Sku record = new Sku();
        record.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(record);
        skus.forEach(sku -> {
            Stock stock = stockMapper.selectByPrimaryKey(sku.getId());
            sku.setStock(stock.getStock());
        });
        return skus;
    }
    
    /* *
     * @Description //TODO 更新商品信息
     * @Date 2020/12/22 9:41
     * @return 
     **/
    @Transactional
    public void updateGoods(SpuBo spuBo) {
        // 根据spuId查询要删除的sku
        Sku record = new Sku();
        record.setSpuId(spuBo.getId());
        List<Sku> skus = skuMapper.select(record);
        // 刪除stock
        skus.forEach(sku -> {
            stockMapper.deleteByPrimaryKey(sku.getId());
        });

        // 根据spu 刪除sku
        skuMapper.delete(record);
        // 更新spu 和spuDetail
        spuBo.setCreateTime(null);
        spuBo.setLastUpdateTime(new Date());
        spuBo.setValid(null);
        spuBo.setSaleable(null);
        saveSkuAndStock(spuBo);
        spuMapper.updateByPrimaryKeySelective(spuBo);
        spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());

        sendMsg("update", spuBo.getId());
    }

    private void saveSkuAndStock(SpuBo spuBo) {
        // 新增sku
        spuBo.getSkus().forEach(sku -> {
            // 新增 sku
            sku.setId(null);
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insertSelective(sku);
            // 新增stock
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insertSelective(stock);
        });
    }
}
