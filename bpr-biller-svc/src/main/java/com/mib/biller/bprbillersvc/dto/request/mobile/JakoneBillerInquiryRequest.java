package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class JakoneBillerInquiryRequest implements Serializable {
    @JsonProperty(value = "kode_billing")
    @NotNull(message = "Field tidak boleh kosong")
    private String bill;
    private String type;


    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
