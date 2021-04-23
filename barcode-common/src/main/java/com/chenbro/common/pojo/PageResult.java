package com.chenbro.common.pojo;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description TODO  分页结果集
 * @Author z8777
 * @Date 2020/12/17 9:41
 * @Version 1.0
 **/
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;


    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
