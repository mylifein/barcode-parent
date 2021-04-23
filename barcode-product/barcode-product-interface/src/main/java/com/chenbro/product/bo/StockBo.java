package com.chenbro.product.bo;

/**
 * @ClassName StockBo
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/31 13:36
 * @Version 1.0
 **/
public class StockBo {

    private String cusMatno;
    private String delMatno;
    private String prodName;
    private Integer toatalQty;
    private String wmName;
    private Long replyId;
    private Integer erpQty;
    private Integer diffQty;
    private String subCode;

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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getToatalQty() {
        return toatalQty;
    }

    public void setToatalQty(Integer toatalQty) {
        this.toatalQty = toatalQty;
    }

    public String getWmName() {
        return wmName;
    }

    public void setWmName(String wmName) {
        this.wmName = wmName;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Integer getErpQty() {
        return erpQty;
    }

    public void setErpQty(Integer erpQty) {
        this.erpQty = erpQty;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Integer getDiffQty() {
        return diffQty;
    }

    public void setDiffQty(Integer diffQty) {
        this.diffQty = diffQty;
    }
}
