package org.saas.qa.domain;

import java.io.Serializable;

public class OrderMaster implements Serializable{
	private String saasOrderKey;
	private Integer reportDate;
	private Long startTime;
	private Long checkoutTime;
	private Integer orderStatus;

	private Double foodAmount;
	private Double promotionAmount;
	private Double paidAmount;
	private AbnormalOrder abnormalOrder;

	public String getSaasOrderKey() {
		return saasOrderKey;
	}
	public void setSaasOrderKey(String saasOrderKey) {
		this.saasOrderKey = saasOrderKey;
	}
	public Integer getReportDate() {
		return reportDate;
	}
	public void setReportDate(Integer reportDate) {
		this.reportDate = reportDate;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(Long checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	public Integer getOrderStatus(){
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus = orderStatus;
	}
	public Double getFoodAmount() {
		return foodAmount;
	}
	public void setFoodAmount(Double foodAmount) {
		this.foodAmount = foodAmount;
	}
	public Double getPromotionAmount() {
		return promotionAmount;
	}
	public void setPromotionAmount(Double promotionAmount) {
		this.promotionAmount = promotionAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public AbnormalOrder getAbnormalOrder() {
		return abnormalOrder;
	}

	public void setAbnormalOrder(AbnormalOrder abnormalOrder) {
		this.abnormalOrder = abnormalOrder;
	}
}
