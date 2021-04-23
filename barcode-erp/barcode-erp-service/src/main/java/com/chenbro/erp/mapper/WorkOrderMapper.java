package com.chenbro.erp.mapper;

import com.chenbro.erp.pojo.ErpCost;
import com.chenbro.erp.pojo.ErpCostType;
import com.chenbro.erp.pojo.ErpStock;
import com.chenbro.erp.pojo.WorkOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WorkOrderMapper extends Mapper<WorkOrder> {


    @Select("select msi.segment1 as delMatno, moq.subinventory_code as subinventoryCode, sum(moq.transaction_quantity) as quantity " +
            "from mtl_onhand_quantities moq, mtl_system_items_b msi " +
            "where moq.organization_id = msi.organization_id " +
            "and moq.inventory_item_id = msi.inventory_item_id " +
            "and moq.organization_id = 572 " +
            "and moq.subinventory_code in ('VMID', 'HB0D', 'SB0D') " +
            "group by msi.segment1, moq.subinventory_code")
    List<ErpStock> selectStocks();


    @Select("select msi.segment1 as delMatno, moq.subinventory_code as subinventoryCode, sum(moq.transaction_quantity) as quantity " +
            "from mtl_onhand_quantities moq, mtl_system_items_b msi " +
            "where moq.organization_id = msi.organization_id " +
            "and moq.inventory_item_id = msi.inventory_item_id " +
            "and moq.organization_id = 572 " +
            "and moq.subinventory_code in 'VMID' " +
            "group by msi.segment1, moq.subinventory_code")
    List<ErpStock> selectVMIStocks();


    @Select({"<script>",
            "SELECT b.segment1, b.description, a.cost_type costType, c.item_cost itemCost, c.material_cost materialCost, c.resource_cost resourceCost, c.outside_processing_cost outCost ,",
            " c.overhead_cost overheadCost, c.based_on_rollup_flag basedOnRollupFlag, b.planning_make_buy_code planningMakeBuyCode FROM apps.cst_cost_types_v a, inv.mtl_system_items_b b, apps.cst_item_costs c ",
            " WHERE c.cost_type_id = a.cost_type_id AND c.inventory_item_id = b.inventory_item_id AND c.organization_id = b.organization_id AND b.inventory_item_status_code = 'Active' ",
            " <if test='orgId != null'>",
            " AND b.organization_id = #{orgId}",
            " </if>",
            " <if test=' costTypes != null and costTypes.size() != 0'>",
            " AND a.cost_type in",
            "<foreach collection = 'costTypes' item = 'costType' open = '(' separator = ',' close = ')'  >",
            " #{costType} ",
            "</foreach>",
            " </if>",
            " <if test='segments != null and segments.size() != 0'>",
            " AND b.segment1 in ",
            "<foreach collection = 'segments' item = 'segment' open = '(' separator = ',' close = ')'  >",
            " #{segment} ",
            "</foreach>",
            " </if>",
            "</script>"})
    List<ErpCost> selectErpCost(@Param("orgId") Integer orgId, @Param("costTypes") List<String> costTypes, @Param("segments") List<String> segments);


    @Select("SELECT a.cost_type costType, a. cost_type_id costTypeId FROM apps.cst_cost_types_v a, inv.mtl_system_items_b b, apps.cst_item_costs c " +
            " WHERE b.organization_id = 572 AND c.cost_type_id = a.cost_type_id AND c.inventory_item_id = b.inventory_item_id " +
            " AND c.organization_id = b.organization_id AND b.inventory_item_status_code = 'Active' AND b.segment1 = '83H569714-006' AND a.cost_type LIKE '20%'  ")
    List<ErpCostType> selectErpCostType();

}
