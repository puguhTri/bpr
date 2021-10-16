package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PdamBillerInquiryRequest {
    @JsonProperty(value = "id_pelanggan")
    @NotNull
    private String clientId;
    private String type;

}
