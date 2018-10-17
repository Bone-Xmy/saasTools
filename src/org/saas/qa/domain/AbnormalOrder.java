package org.saas.qa.domain;

import java.io.Serializable;

public class AbnormalOrder implements Serializable{
    private String saasOrderKey;
    private Integer type;
    private String description;
    private String reason;

    public String getSaasOrderKey() {
        return saasOrderKey;
    }

    public void setSaasOrderKey(String saasOrderKey) {
        this.saasOrderKey = saasOrderKey;
    }

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
