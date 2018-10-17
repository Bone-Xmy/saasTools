package org.saas.qa.domain;

import java.io.Serializable;

public class OrderPay implements Serializable{

	private Integer rowId;
	private String paySubjectName;
	private String paySubjectCode;
	private Integer isJoinReceived;
	private Double debitAmount;
	private Double paySubjectRealAmount;
	private OrderMaster orderMaster;
	
	public Integer getRowId(){
		return rowId;
	}
	public void setRowId(Integer rowId){
		this.rowId = rowId;
	}
	
	public String getPaySubjectName() {
		return paySubjectName;
	}
	public void setPaySubjectName(String paySubjectName) {
		this.paySubjectName = paySubjectName;
	}
	public Integer getIsJoinReceived(){
		return isJoinReceived;
	}
	public void setIsJoinReceived(Integer isJoinReceived){
		this.isJoinReceived = isJoinReceived;
	}
	public String getPaySubjectCode(){
		return paySubjectCode;
	}
	public void getPaySubjectCode(String paySubjectCode){
		this.paySubjectCode = paySubjectCode;
	}
	public Double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public Double getPaySubjectRealAmount() {
		return paySubjectRealAmount;
	}
	public void setPaySubjectRealAmount(Double paySubjectRealAmount) {
		this.paySubjectRealAmount = paySubjectRealAmount;
	}
	public OrderMaster getOrderMaster() {
		return orderMaster;
	}
	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}
}
