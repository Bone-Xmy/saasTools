package org.saas.qa.dao;

import org.apache.ibatis.annotations.*;
import org.saas.qa.domain.AbnormalOrder;

import java.util.List;

public interface AbnormalOrderDao {
    //查询异常账单
    /*
    @Select("select orderKey,1 as type,concat('流水：',convert(foodAmount,char),'，优惠金额：',convert(promotionAmount,char),',实收金额：',convert(paidAmount,char),',差值：',convert(round((foodAmount - promotionAmount - paidAmount),2),char)) as description\n" +
            "from tbl_mendian_order_master where round(foodAmount,4) <> round((promotionAmount + paidAmount),4) and orderStatus = 40\n" +
            "union all\n" +
            "select orderKey,2 as type,concat('流水：',convert(foodAmount,char),'，支付科目合计：',convert((select sum(debitAmount) from tbl_mendian_order_pay as p where p.orderKey = m.orderKey),char),'，差值：',convert(foodAmount - (select sum(debitAmount) from tbl_mendian_order_pay as p where p.orderKey = m.orderKey),char))  as description from tbl_mendian_order_master as m where (case when round(foodAmount,4) <> round((select sum(debitAmount) from tbl_mendian_order_pay as p where p.orderKey = m.orderKey),4) then 'YC' else '' end) = 'YC'\n" +
            "union all\n" +
            "select orderKey,3 as type,concat('流水：',convert(foodAmount,char),'，菜品销售金额合计：',convert((select sum(foodPriceAmount) from tbl_mendian_order_food as f where f.orderKey = m.orderKey),char),'，差值：',convert(foodAmount - (select sum(foodPriceAmount) from tbl_mendian_order_food as f where f.orderKey = m.orderKey),char)) as description from tbl_mendian_order_master as m where (case when round(foodAmount,4) <> round((select sum(foodPriceAmount) from tbl_mendian_order_food as f where f.orderKey = m.orderKey),4) then 'CJYC' else '' end) = 'CJYC'\n" +
            "union all \n" +
            "select orderKey,4 as type,concat('实收：',convert(paidAmount,char),',实收科目合计：',convert((select sum(paySubjectRealAmount) from tbl_mendian_order_pay as p where p.orderKey = m.orderKey),char),'，差值：',convert(paidAmount - (select sum(paySubjectRealAmount) from tbl_mendian_order_pay as p where p.orderKey = m.orderKey),char)) as description from tbl_mendian_order_master as m where (case when round(paidAmount,4) <> round((select sum(paySubjectRealAmount) from tbl_mendian_order_pay as p where p.orderKey = m.orderKey),4) then 'SSYC' else '' end) = 'SSYC'\n" +
            "union all \n" +
            "select orderKey,5 as type,concat('实收：',convert(paidAmount,char),',菜品实收合计：',convert((select sum(foodRealAmount) from tbl_mendian_order_food as f where f.orderKey = m.orderKey),char),'，差值：',convert(paidAmount - (select sum(foodRealAmount) from tbl_mendian_order_food as f where f.orderKey = m.orderKey),char)) as description from tbl_mendian_order_master as m where (case when round(paidAmount,4) <> round((select sum(foodRealAmount) from tbl_mendian_order_food as f where f.orderKey = m.orderKey),4) then 'CPSSYC' else '' end) = 'CPSSYC'\n")
            */
    
    @Select("select saasOrderKey,1 as type,'流水：' || foodAmount || '，优惠金额：' || promotionAmount || ',实收金额：' || paidAmount || ',差值：' || round((foodAmount - promotionAmount - paidAmount),2) as description from tbl_saas_order_master where round(foodAmount,4) <> round((promotionAmount + paidAmount),4) and orderStatus = 40 \n" +
            " union all\n" +
            " select saasOrderKey,2 as type,'流水：' || foodAmount || '，支付科目合计：' || (select sum(debitAmount) from tbl_saas_order_pay as p where p.saasOrderKey = m.saasOrderKey) || '，差值：' || cast(foodAmount - (select sum(debitAmount) from tbl_saas_order_pay as p where p.saasOrderKey = m.saasOrderKey) as char) as description from tbl_saas_order_master as m where (case when round(foodAmount,4) <> round((select sum(debitAmount) from tbl_saas_order_pay as p where p.saasOrderKey = m.saasOrderKey),4) then 'YC' else '' end) = 'YC' \n" +
            " union all\n" +
            " select saasOrderKey,3 as type,'流水：' || foodAmount || '，菜品销售金额合计：' || (select sum(foodPriceAmount) from tbl_saas_order_food as f where f.saasOrderKey = m.saasOrderKey) || '，差值：' || cast(foodAmount - (select sum(foodPriceAmount) from tbl_saas_order_food as f where f.saasOrderKey = m.saasOrderKey) as char) as description from tbl_saas_order_master as m where (case when round(foodAmount,4) <> round((select sum(foodPriceAmount) from tbl_saas_order_food as f where f.saasOrderKey = m.saasOrderKey),4) then 'CJYC' else '' end) = 'CJYC' \n" +
            " union all\n" +
            " select saasOrderKey,4 as type,'实收：' || paidAmount || ',实收科目合计：' || (select sum(paySubjectRealAmount) from tbl_saas_order_pay as p where p.saasOrderKey = m.saasOrderKey) || '，差值：' || cast(paidAmount - (select sum(paySubjectRealAmount) from tbl_saas_order_pay as p where p.saasOrderKey = m.saasOrderKey) as char) as description from tbl_saas_order_master as m where (case when round(paidAmount,4) <> round((select sum(paySubjectRealAmount) from tbl_saas_order_pay as p where p.saasOrderKey = m.saasOrderKey),4) then 'SSYC' else '' end) = 'SSYC' \n" +
            " union all\n" +
            " select saasOrderKey,5 as type,'实收：' || paidAmount || ',菜品实收合计：' || (select sum(foodRealAmount) from tbl_saas_order_food as f where f.saasOrderKey = m.saasOrderKey) || '，差值：' || cast(paidAmount - (select sum(foodRealAmount) from tbl_saas_order_food as f where f.saasOrderKey = m.saasOrderKey) as char) as description from tbl_saas_order_master as m where (case when round(paidAmount,4) <> round((select sum(foodRealAmount) from tbl_saas_order_food as f where f.saasOrderKey = m.saasOrderKey),4) then 'CPSSYC' else '' end) = 'CPSSYC' \n")
    List<AbnormalOrder> selectAllAbnormalOrder();
    //可能的原因
    //是否进行了反结账，且反结账没有完成
    
}

