package org.saas.qa.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.saas.qa.domain.OrderPay;
import static org.saas.qa.util.common.SaasToolsConstants.PAYTABLE;

public class OrderPayDynaProvider {
	//分页动态查询
	public String selectWithParam(Map<String,Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(PAYTABLE);
				System.out.println("orderPay 是否为null : " + (params.get("orderPay") != null));
				if(params.get("orderPay") != null) {
					OrderPay orderPay = (OrderPay)params.get("orderPay");
					if(orderPay.getOrderMaster() != null && orderPay.getOrderMaster().getSaasOrderKey() != null) {
						WHERE(" saasOrderKey = #{orderPay.orderMaster.saasOrderKey}");
					}
					//System.out.println("getDebitAmount的值为是否为0：");
					//System.out.println("getDebitAmount的值为是否为0：" + orderPay.getDebitAmount() != null);
					if(orderPay.getDebitAmount() != null) {
						WHERE(" debitAmount = #{debitAmount}");
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
				FROM(PAYTABLE);
				if(params.get("orderPay") != null) {
					OrderPay orderPay = (OrderPay)params.get("orderPay");
					if(orderPay.getOrderMaster() != null && orderPay.getOrderMaster().getSaasOrderKey() != null) {
						WHERE(" saasOrderKey = #{saasOrderKey}");
					}
					if(orderPay.getPaySubjectCode() != null && !orderPay.getPaySubjectCode().equals("")) {
						WHERE(" paySubjectCode = #{paySubjectCode}");
					}
				}
			}	
		}.toString();
	}
	//动态插入
	public String insertOrderPay(OrderPay orderPay) {
		return new SQL() {
			{
				INSERT_INTO(PAYTABLE);
				if(orderPay.getPaySubjectName() != null) {
					VALUES("paySubjectName","#{paySubjectName}");
				}
				if(orderPay.getDebitAmount() != 0) {
					VALUES("debitAmount","#{debitAmount}");
				}
				if(!(orderPay.getIsJoinReceived() < 0)) {
					VALUES("isJoinReceived","#{isJoinReceived}");
				}
			}
		}.toString();
	}
	//动态更新
	public String updateOrderPay(OrderPay orderPay) {
		return new SQL() {
			{
				UPDATE(PAYTABLE);
				if(orderPay.getOrderMaster().getSaasOrderKey() != null) {
					SET(" saasOrderKey = #{saasOrderKey}");
				}
				if(orderPay.getPaySubjectName() != null) {
					SET("paySubjectName = #{paySubjectName}");
				}
				if(orderPay.getPaySubjectCode() != null) {
					WHERE("paySubjectCode = #{paySubjectCode}");
				}
			}
		}.toString();
	}
}
