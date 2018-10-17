package org.saas.qa.dao;

import org.apache.ibatis.annotations.*;
import org.saas.qa.dao.provider.OrderMasterDynaSqlProvider;
import org.saas.qa.domain.OrderMaster;

import static org.saas.qa.util.common.SaasToolsConstants.MASTERTABLE;

import java.util.List;
import java.util.Map;

public interface OrderMasterDao {
	//根据saasOrderKey查询订单
	@Select("select * from " + MASTERTABLE + " where saasOrderKey = #{saasOrderKey}")
	OrderMaster selectBySaasOrderKey(@Param("saasOrderKey") String saasOrderKey);
	//根据saasOrderKey删除订单
	@Delete("delete from " + MASTERTABLE + " where saasOrderKey = #{saasOrderKey}")
	void deleteBySaasOrderKey(@Param("saasOrderKey") String saasOrderKey);
	//动态修改用户
	@UpdateProvider(type=OrderMasterDynaSqlProvider.class,method="updateOrderMaster")
	void update(OrderMaster orderMaster);
	//动态查询
	@SelectProvider(type=OrderMasterDynaSqlProvider.class,method="selectWithParam")
	List<OrderMaster> selectByPage(Map<String,Object> params);
	//根据参数查询订单总数
	@SelectProvider(type=OrderMasterDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@InsertProvider(type=OrderMasterDynaSqlProvider.class,method="insertOrderMaster")
	void save(OrderMaster orderMaster);
	//查询所有账单
	@Select("select * from " + MASTERTABLE + " ") //为什么需要加空格
	List<OrderMaster> selectAllOrder();
	
}
