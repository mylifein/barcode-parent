package com.chenbro.product.pojo;

import javax.persistence.*;
import java.sql.Date;

/**
 * @ClassName Stock
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 10:31
 * @Version 1.0
 **/
@Table(name = "vmi_stock")
public class Stock {

    @Id
    private String uuid;

    @Column(name = "cus_matno")
    private String cusMatno;

    @Column(name = "del_matno")
    private String delMatno;

    private String dn;

    @Column(name = "prod_name")
    private String prodName;

    @Column(name = "prod_qty")
    private Integer prodQty;

    @Column(name = "wm_name")
    private String wmName;

    @Column(name = "store_date")
    private Date storeDate;

    @Column(name = "stock_days")
    private Integer stockDays;

    @Column(name = "plan_due")
    private Date planDue;

    @Column(name = "prod_owner")
    private String prodOwner;

    private String supplier;

    @Column(name = "prod_prop")
    private String prodProp;

    private String remark;

    @Column(name = "reply_id")
    private Long replyId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCusMatno() {
        return cusMatno;
    }

    public void setCusMatno(String cusMatno) {
        this.cusMatno = cusMatno;
    }

    public String getDelMatno() {
        return delMatno;
    }

    public void setDelMatno(String delMatno) {
        this.delMatno = delMatno;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdQty() {
        return prodQty;
    }

    public void setProdQty(Integer prodQty) {
        this.prodQty = prodQty;
    }

    public String getWmName() {
        return wmName;
    }

    public void setWmName(String wmName) {
        this.wmName = wmName;
    }

    public Date getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(Date storeDate) {
        this.storeDate = storeDate;
    }

    public Integer getStockDays() {
        return stockDays;
    }

    public void setStockDays(Integer stockDays) {
        this.stockDays = stockDays;
    }

    public Date getPlanDue() {
        return planDue;
    }

    public void setPlanDue(Date planDue) {
        this.planDue = planDue;
    }

    public String getProdOwner() {
        return prodOwner;
    }

    public void setProdOwner(String prodOwner) {
        this.prodOwner = prodOwner;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProdProp() {
        return prodProp;
    }

    public void setProdProp(String prodProp) {
        this.prodProp = prodProp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }
}
