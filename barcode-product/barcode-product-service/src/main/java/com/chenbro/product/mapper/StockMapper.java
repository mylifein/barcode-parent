package com.chenbro.product.mapper;

import com.chenbro.product.bo.StockBo;
import com.chenbro.product.pojo.Stock;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName StockMapper
 * @Description TODO
 * @Author z8777
 * @Date 2021/3/26 15:14
 * @Version 1.0
 **/
public interface StockMapper extends Mapper<Stock> {


    @Select("SELECT uuid, cus_matno, del_matno, prod_name, prod_qty, wm_name, store_date, stock_days, plan_due, prod_owner, supplier, prod_prop, remark, reply_id FROM vmi_stock WHERE reply_id = (SELECT MAX(reply_id) FROM vmi_stock)")
    @Results(id = "stockMap", value = {
            @Result(id = true, property = "uuid", column = "uuid"),
            @Result(property = "cusMatno", column = "cus_matno"),
            @Result(property = "delMatno", column = "del_matno"),
            @Result(property = "dn", column = "dn"),
            @Result(property = "prodName", column = "prod_name"),
            @Result(property = "prodQty", column = "prod_qty"),
            @Result(property = "wmName", column = "wm_name"),
            @Result(property = "storeDate", column = "store_date"),
            @Result(property = "stockDays", column = "stock_days"),
            @Result(property = "planDue", column = "plan_due"),
            @Result(property = "prodOwner", column = "prod_owner"),
            @Result(property = "supplier", column = "supplier"),
            @Result(property = "prodProp", column = "prod_prop"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "replyId", column = "reply_id")
    })
    List<Stock> selectStocksByCurrentReplyId();


    @Select("SELECT cus_matno cusMatno, del_matno delMatno, prod_name prodName, SUM(prod_qty) toatalQty, wm_name wmName, reply_id replyId FROM vmi_stock WHERE " +
            "reply_id = ( SELECT MAX(reply_id) FROM vmi_stock) GROUP BY  cus_matno, del_matno, prod_name, wm_name, reply_id")
    List<StockBo> selectStocksByGroup();

}
