package org.saas.qa.domain;

import java.io.Serializable;

public class OrderCheck implements Serializable{

	private Integer id;
	private String saasOrderKey;
	private String desc;
	private String diff;
	private Integer reason;
	
	public OrderCheck() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSaasOrderKey() {
		return saasOrderKey;
	}

	public void setSaasOrderKey(String saasOrderKey) {
		this.saasOrderKey = saasOrderKey;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDiff() {
		return diff;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}
}
