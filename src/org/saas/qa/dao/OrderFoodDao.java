package org.saas.qa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.saas.qa.dao.provider.OrderFoodDynaSqlProvider;
import org.saas.qa.domain.OrderFood;
import static org.saas.qa.util.common.SaasToolsConstants.FOODTABLE;

public interface OrderFoodDao {
	//根据参数查询菜品总数
	@SelectProvider(type=OrderFoodDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//根据参数动态查询菜品
	@SelectProvider(type=OrderFoodDynaSqlProvider.class,method="selectWithParam")
	@Results({
		//"id=true"什么意思？
		@Result(id=true,column="itemID",property="itemID"),
		@Result(column="itemKey",property="itemKey"),
		@Result(column="foodName",property="foodName"),
		@Result(column="foodProPrice",property="foodProPrice"),
		//参考
		//@Result(column="startTime",property="startTime",javaType=java.util.Date.class),
		@Result(column="saasOrderKey",property="orderMaster",
				one=@One(select="org.saas.qa.dao.OrderMasterDao.selectBySaasOrderKey",
			fetchType=FetchType.EAGER))
	})
	List<OrderFood> selctByPage(Map<String,Object> params);
	//动态插入菜品
	@SelectProvider(type=OrderFoodDynaSqlProvider.class,method="insertOrderFood")
	void save(OrderFood orderFood);
	//根据saasOrderKey删除菜品
	@Delete(" delete from " + FOODTABLE + "where saasOrderKey = #{saasOrderKey}")
	void deleteBySaasOrderKey(String saasOrderKey);
	//根据saasOrderKey查询菜品数据
	@Select("select * from " + FOODTABLE + "where saasOrderKey = #{saasOrderKey}")
	@Results({
		@Result(id=true,column="itemID",property="itemID"),
		@Result(column="itemKey",property="itemKey"),
		@Result(column="foodName",property="foodName"),
		@Result(column="foodProPrice",property="foodProPrice"),
		@Result(column="saasOrderKey",property="saasOrderKey",
			one=@One(select="org.saas.qa.dao.OrderMasterDao.selectBySaasOrderKey",
			fetchType=FetchType.EAGER))
	})
	OrderFood selectBySaasOrderKey(String saasOrderKey);
	//动态修改菜品
	@UpdateProvider(type=OrderFoodDynaSqlProvider.class,method="updateOrderFood")
	void update(OrderFood orderFood);
}
