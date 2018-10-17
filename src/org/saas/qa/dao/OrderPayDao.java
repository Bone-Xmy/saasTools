package org.saas.qa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.saas.qa.dao.provider.OrderFoodDynaSqlProvider;
import org.saas.qa.dao.provider.OrderPayDynaProvider;
import org.saas.qa.domain.OrderFood;
import org.saas.qa.domain.OrderPay;

import static org.saas.qa.util.common.SaasToolsConstants.FOODTABLE;
import static org.saas.qa.util.common.SaasToolsConstants.PAYTABLE;

public interface OrderPayDao {
	//根据saasOrderKey查询支付明细
	@SelectProvider(type=OrderPayDynaProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//根据参数动态查询员工
	@SelectProvider(type=OrderPayDynaProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="rowId",property="rowId"),
		@Result(column="paySubjectName",property="paySubjectName"),
		@Result(column="debitAmount",property="debitAmount"),
		@Result(column="saasOrderKey",property="orderMaster",
			one=@One(select="org.saas.qa.dao.OrderMasterDao.selectBySaasOrderKey",
		fetchType=FetchType.EAGER))
	})
	List<OrderPay> selectByPage(Map<String,Object> params);
	//动态插入支付科目
	@InsertProvider(type=OrderPayDynaProvider.class,method="insertOrderPay")
	void save(OrderPay orderPay);
	//根据saasOrderKey删除员工
	@Delete(" delete from " + PAYTABLE + " where saasOrderKey = #{saasOrderKey} ")
	void deleteBySaasOrderKey(String saasOrderKey);
	//根据saasOrderKey查询支付数据
	@Select("select * from " + PAYTABLE + "where saasOrderKey = #{saasOrderKey}")
	@Results({
		@Result(id=true,column="rowId",property="rowId"),
		@Result(column="paySubjectName",property="paySubjectName"),
		@Result(column="debitAmount",property="debitAmount"),
		@Result(column="saasOrderKey",property="saasOrderKey",
			one=@One(select="org.saas.qa.dao.OrderMaster.selectBySaasOrderKey",
			fetchType=FetchType.EAGER))
	})
	OrderPay selectBySaasOrderKey(String saasOrderKey);
	//动态修改科目
	@UpdateProvider(type=OrderPayDynaProvider.class,method="updateOrderPay")
	void update(OrderPay orderPay);
	
}
