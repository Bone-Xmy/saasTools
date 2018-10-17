package org.saas.qa.domain;

import java.io.Serializable;

public class OrderFood implements Serializable{

	private Integer itemID;
	private String foodName;
	private String itemKey;
	private Double foodProPrice;
	private Double foodPriceAmount;
	private Double foodRealAmount;
	private OrderMaster orderMaster;
	
	public Integer getItemID(){
		return itemID;
	}
	public void setItemID(Integer itemID){
		this.itemID = itemID;
	}

	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getItemKey(){
		return itemKey;
	}
	public void setItemKey(String itemKey){
		this.itemKey = itemKey;
	}
	public Double getFoodProPrice() {
		return foodProPrice;
	}
	public void setFoodProPrice(Double foodProPrice) {
		this.foodProPrice = foodProPrice;
	}
	public Double getFoodPriceAmount() {
		return foodPriceAmount;
	}
	public void setFoodPriceAmount(Double foodPriceAmount) {
		this.foodPriceAmount = foodPriceAmount;
	}
	public Double getFoodRealAmount() {
		return foodRealAmount;
	}
	public void setFoodRealAmount(Double foodRealAmount) {
		this.foodRealAmount = foodRealAmount;
	}
	public OrderMaster getOrderMaster() {
		return orderMaster;
	}
	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}
	
}
