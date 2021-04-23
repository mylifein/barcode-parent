package com.chenbro.erp.pojo;

import javax.persistence.Column;

/**
 * @ClassName ErpStock
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/17 10:31
 * @Version 1.0
 **/

public class ErpStock {


    private String delMatno;
    private Integer quantity;
    private String subinventoryCode;


    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }
}
