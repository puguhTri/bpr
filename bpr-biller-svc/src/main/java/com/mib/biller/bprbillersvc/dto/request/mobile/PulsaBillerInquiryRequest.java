package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PulsaBillerInquiryRequest {
    @JsonProperty(value = "phone")
    @NotNull
    private String phoneNumber;
    private String type;
}
