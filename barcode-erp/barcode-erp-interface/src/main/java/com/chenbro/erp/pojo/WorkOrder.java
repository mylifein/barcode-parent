package com.chenbro.erp.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName WorkOrder
 * @Description TODO
 * @Author z8777
 * @Date 2021/2/6 19:47
 * @Version 1.0
 **/
@Table(name = "wip.wip_entities")
public class WorkOrder {

    @Id
    @Column(name = "WIP_ENTITY_ID")
    private String id;

    @Column(name = "WIP_ENTITY_NAME")
    private String woNo;

    @Column(name = "ORGANIZATION_ID")
    private String orgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWoNo() {
        return woNo;
    }

    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
