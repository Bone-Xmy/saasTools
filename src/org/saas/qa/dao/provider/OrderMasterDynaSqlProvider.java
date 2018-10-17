package org.saas.qa.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.saas.qa.domain.OrderMaster;

import static org.saas.qa.util.common.SaasToolsConstants.MASTERTABLE;

public class OrderMasterDynaSqlProvider {
	//分页动态查询
	public String selectWithParam(Map<String,Object> params) {
		//***如何理解？
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(MASTERTABLE);
				if(params.get("orderMaster") != null) {
					OrderMaster orderMaster = (OrderMaster)params.get("orderMaster");
					if(orderMaster.getSaasOrderKey() != null && !orderMaster.getSaasOrderKey().equals("")) {
						WHERE(" saasOrderKey = #{orderMaster.saasOrderKey}");
					}
					//查询对应的订单状态，怎么判断integer类型的值;下面的写法是否正确，为什么？
					if(orderMaster.getOrderStatus() != null && !orderMaster.getOrderStatus().equals("")) {
						//为什么上面需要使用getter方法，此处直接访问orderMaster的属性
						WHERE(" orderStatus = #{orderMaster.orderStatus}");
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		return sql;
	}
	//动态查询总数量
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(MASTERTABLE);
				if(params.get("orderMaster") != null) {
					OrderMaster orderMaster = (OrderMaster)params.get("orderMaster");
					if(orderMaster.getSaasOrderKey() != null && !orderMaster.getSaasOrderKey().equals("")) {
						WHERE(" saasOrderKey = #{orderMaster.saasOrderKey}");
					}
					if(orderMaster.getOrderStatus() != null && !orderMaster.getOrderStatus().equals("")) {
						WHERE(" orderStatus = #{orderMaster.orderStatus}");
					}
				}
			
			}
		}.toString();
	}
	//动态插入
	public String insertOrderMaster(OrderMaster orderMaster) {
		return new SQL() {
			{
				INSERT_INTO(MASTERTABLE);
				if(orderMaster.getSaasOrderKey() != null && !orderMaster.getSaasOrderKey().equals("")) {
					VALUES("saasOrderKey","#{username}");
				}
				if(orderMaster.getOrderStatus() != null && !orderMaster.getOrderStatus().equals("")) {
					VALUES("orderStatus","#{orderStatus}");
				}
			}
		}.toString();
	}
	//动态更新
	public String updateOrderMaster(OrderMaster orderMaster) {
		return new SQL() {
			{
				UPDATE(MASTERTABLE);
				if(orderMaster.getReportDate() != null) {
					SET( "reportDate = #{reportDate}");
				}
				if(orderMaster.getFoodAmount() != null) {
					SET(" foodAmount = #{foodAmount}");
				}
				WHERE(" saasOrderKey = #{saasOrderKey}");
			}
		}.toString();
	}
}
