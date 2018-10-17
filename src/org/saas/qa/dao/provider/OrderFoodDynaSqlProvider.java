package org.saas.qa.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.saas.qa.domain.OrderFood;

import static org.saas.qa.util.common.SaasToolsConstants.FOODTABLE;
public class OrderFoodDynaSqlProvider {
	//分页动态查询
	public String selectWithParam(Map<String,Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(FOODTABLE);
				System.out.println("provier 方法 orderFood 是否为null : " + (params.get("orderFood") != null));
				if(params.get("orderFood") != null) {
					OrderFood orderFood = (OrderFood)params.get("orderFood");
					//System.out.println("orderFood.getOrderMaster() -- >> " + orderFood.getOrderMaster() != null);
					//System.out.println("orderFood.getOrderMaster().getSaasOrderKey() -- >> " + orderFood.getOrderMaster().getSaasOrderKey() != null);
					//System.out.println("orderFood.getOrderMaster().getSaasOrderKey() -- >>  " + orderFood.getOrderMaster().getSaasOrderKey() != null );
					if(orderFood.getOrderMaster() != null && orderFood.getOrderMaster().getSaasOrderKey() != null && orderFood.getOrderMaster().getSaasOrderKey() != "0") { //0是随便写的，根据实际情况修改
						WHERE(" saasOrderKey = #{orderFood.orderMaster.saasOrderKey}");
					}
					//if(employee.getJo() != null
					// && employee.getJob().getId() != null && ...){
					//	WHERE( "JOB_ID = #{employee.job.id} ");
					//}
					if(orderFood.getFoodName() != null && !orderFood.getFoodName().equals("")) {
						WHERE(" foodName = #{foodName}");
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
				FROM(FOODTABLE);
				if(params.get("orderFood") != null) {
					OrderFood orderFood = (OrderFood)params.get("orderFood");
					if(orderFood.getOrderMaster() != null && orderFood.getOrderMaster().getSaasOrderKey() != null) {
						WHERE(" saasOrderKey = #{orderFood.orderMaster.saasOrderKey} ");
					}
					if(orderFood.getFoodName() != null && !orderFood.getFoodName().equals("")) {
						WHERE(" foodName = #{orderFood.foodName} ");
					}
				}
			}
		}.toString();
	}
	//动态插入
	public String insertOrderFood(OrderFood orderFood) {
		return new SQL() {
			{
				INSERT_INTO(FOODTABLE);
				if(orderFood.getFoodName() != null) {
					VALUES("foodName","#{foodName}");
				}
				if(orderFood.getFoodProPrice() != null) {
					VALUES("foodProPrice","#{foodProPrice}");
				}
			}
		}.toString();
	}
	//动态更新
	public String updateOrderFood(OrderFood orderFood) {
		return new SQL() {
			{
				UPDATE(FOODTABLE);
				if(orderFood.getFoodName() != null) {
					SET(" foodName = #{foodName}");
				}
				if(orderFood.getFoodProPrice() > 0) {
					SET(" foodProPrice = #{foodProPrice}");
				}
			}
		}.toString();
	}
}
