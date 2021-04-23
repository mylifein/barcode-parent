package com.chenbro.erp.pojo;

/**
 * @ClassName ErpCost
 * @Description TODO
 * @Author z8777
 * @Date 2021/4/8 11:37
 * @Version 1.0
 **/
public class ErpCost {

    private String segment1;
    private String description;
    private String costType;
    private Double itemCost;
    private Double materialCost;
    private Double resourceCost;
    private Double outCost;
    private Double overheadCost;
    private String basedOnRollupFlag;
    private String planningMakeBuyCode;

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getBasedOnRollupFlag() {
        return basedOnRollupFlag;
    }

    public void setBasedOnRollupFlag(String basedOnRollupFlag) {
        this.basedOnRollupFlag = basedOnRollupFlag;
    }

    public String getPlanningMakeBuyCode() {
        return planningMakeBuyCode;
    }

    public void setPlanningMakeBuyCode(String planningMakeBuyCode) {
        this.planningMakeBuyCode = planningMakeBuyCode;
    }

    public Double getItemCost() {
        return itemCost;
    }

    public void setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }

    public Double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }

    public Double getResourceCost() {
        return resourceCost;
    }

    public void setResourceCost(Double resourceCost) {
        this.resourceCost = resourceCost;
    }

    public Double getOutCost() {
        return outCost;
    }

    public void setOutCost(Double outCost) {
        this.outCost = outCost;
    }

    public Double getOverheadCost() {
        return overheadCost;
    }

    public void setOverheadCost(Double overheadCost) {
        this.overheadCost = overheadCost;
    }
}
