package com.mib.biller.bprbillersvc.dto.request.mobile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PdamBillerInquiryRequest {
    @JsonProperty(value = "id_pelanggan")
    private String clientId;
    private String type;

}
